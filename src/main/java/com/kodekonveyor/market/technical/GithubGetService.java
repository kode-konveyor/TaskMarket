package com.kodekonveyor.market.technical;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.kodekonveyor.SpringConfig;
import com.kodekonveyor.annotations.ExcludeFromCodeCoverage;
import com.kodekonveyor.logging.LoggingMarkerConstants;
import com.kodekonveyor.market.proxies.ObjectMapperService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import static com.kodekonveyor.market.technical.GithubConstants.GITHUB_GET_CALL_FAILURE;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Service
@ExcludeFromCodeCoverage("interface to underlying framework")
public class GithubGetService {

  @Autowired
  private Logger loggerService;

  @Autowired
  private ObjectMapperService objectMapperProxy;

  @Autowired
  private SpringConfig springConfig;

  public <ValueType> ValueType
      call(final String command, final Class<ValueType> cls) {
    final OkHttpClient client = new OkHttpClient().newBuilder().build();
    final URL url = createGithubUrl(command);
    final Request request = new Request.Builder()
          .url(url)
          .get()
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
        return objectMapperProxy.call(contentStream, cls);
      }
      throw new ResponseStatusException(
              HttpStatus.valueOf(response.code()),
              String.format(GITHUB_GET_CALL_FAILURE, response.code())
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

  private URL createGithubUrl(final String command) {
    final String uri = GithubConstants.GITHUB_API_URL_BASE + command;
    loggerService.debug(LoggingMarkerConstants.GITHUB, uri);
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

}
