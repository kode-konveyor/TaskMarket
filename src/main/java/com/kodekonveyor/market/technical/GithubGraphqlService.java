package com.kodekonveyor.market.technical;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.kodekonveyor.SpringConfig;
import com.kodekonveyor.annotations.ExcludeFromCodeCoverage;
import com.kodekonveyor.logging.LoggingMarkerConstants;

@Service
@ExcludeFromCodeCoverage("interface to underlying framework")
public class GithubGraphqlService {

  @Autowired
  private Logger loggerService;

  @Autowired
  private SpringConfig springConfig;

  public DocumentContext call(final String command) {
    try {
      final String issueToken = springConfig.getIssueToken();
      loggerService.debug(LoggingMarkerConstants.GITHUB, command);
      final JSONObject inputJson = new JSONObject();
      inputJson.put(GithubConstants.QUERY, command);
      final URL url = new URL(GithubConstants.GITHUB_GRAPHQL_ENDPOINT);

      final HttpURLConnection con = (HttpURLConnection) url.openConnection();
      con.setRequestMethod(GithubConstants.POST);
      con.setRequestProperty(
          GithubConstants.CONTENT_TYPE, GithubConstants.APPLICATION_JSON_UTF_8
      );
      con.setRequestProperty(
          GithubConstants.AUTHORIZATION,
          String.format(GithubConstants.BEARER, issueToken)
      );
      con.setDoOutput(true);
      final byte[] input = inputJson.toString().getBytes(GithubConstants.UTF_8);
      try (OutputStream outputStream = con.getOutputStream()) {
        outputStream.write(input, 0, input.length);
      }
      return JsonPath.parse(con.getInputStream());
    } catch (final IOException | JSONException exception) {
      throw new ResponseStatusException(
          HttpStatus.INTERNAL_SERVER_ERROR, GithubConstants.INTERNAL_ERROR,
          exception
      );
    }
  }

}
