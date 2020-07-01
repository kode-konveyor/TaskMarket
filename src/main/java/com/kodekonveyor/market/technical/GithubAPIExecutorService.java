package com.kodekonveyor.market.technical;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kodekonveyor.SpringConfig;
import com.kodekonveyor.annotations.ExcludeFromCodeCoverage;
import com.kodekonveyor.market.proxies.ObjectMapperService;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

import static com.kodekonveyor.market.technical.GithubConstants.GITHUB_API_CALL_FAILURE;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
@ExcludeFromCodeCoverage("interface to underlying framework")
public class GithubAPIExecutorService {

    @Autowired
    private ObjectMapperService objectMapperProxy;

    @Autowired
    private SpringConfig springConfig;

    @Autowired
    private ObjectMapper objectMapper;

    public <ValueType> ValueType call(
            final HttpMethod method,
            final String path,
            final Object body,
            final Class<ValueType> type
    ) {
        final OkHttpClient client = new OkHttpClient().newBuilder().build();
        final URL url = createGithubUrl(path);
        final Request request = new Request.Builder()
                .url(url)
                .method(method.name(), createBody(body))
                .addHeader(
                        AUTHORIZATION,
                        String.format(GithubConstants.BEARER, springConfig.getIssueToken())
                )
                .build();

        try (
                Response response = client.newCall(request).execute();
                InputStream contentStream = response.body().byteStream()
        ) {
            if (response.isSuccessful()) {
                return objectMapperProxy.call(contentStream, type);
            }
            throw new ResponseStatusException(
                    HttpStatus.valueOf(response.code()),
                    String.format(GITHUB_API_CALL_FAILURE, response.code())
            );
        } catch (JsonMappingException exception) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    GithubConstants.CANNOT_CONVERT_TO_REQUIED_VALUE_TYPE, exception);
        } catch (final IOException exception) {
            throw new ResponseStatusException(
                    HttpStatus.SERVICE_UNAVAILABLE,
                    GithubConstants.CANNOT_CONNECT_TO_GITHUB, exception
            );
        }
    }

    private URL createGithubUrl(final String path) {
        final String uri = GithubConstants.GITHUB_API_URL_BASE + path;
        URL url;
        try {
            url = new URL(uri);
        } catch (final MalformedURLException exception) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, GithubConstants.INTERNAL_ERROR,
                    exception
            );
        }
        return url;
    }

    private RequestBody createBody(final Object object) {
        if (Objects.isNull(object)) {
            return null;
        }
        try {
            String requestContent = objectMapper.writeValueAsString(object);
            MediaType mediaType = MediaType.parse(APPLICATION_JSON_VALUE);
            return RequestBody.create(mediaType, requestContent);
        } catch (final JsonProcessingException exception) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, GithubConstants.FAILED_TO_CONVERT_TO_JSON,
                    exception
            );
        }
    }

}
