package com.kodekonveyor.market.github;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.kodekonveyor.annotations.ExcludeFromCodeCoverage;
import com.kodekonveyor.logging.LoggingMarkerConstants;
import com.kodekonveyor.market.proxies.ObjectMapperService;

@Service
@ExcludeFromCodeCoverage("interface to underlying framework")
public class GithubGetService {

  @Autowired
  private Logger loggerService;

  @Autowired
  private ObjectMapperService objectMapperProxy;

  public <ValueType> ValueType
      call(final String command, final Class<ValueType> cls) {
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

    final ValueType value;
    try {
      value = objectMapperProxy.call(url, cls);
    } catch (final IOException exception) {
      throw new ResponseStatusException(
          HttpStatus.SERVICE_UNAVAILABLE,
          GithubConstants.CANNOT_CONNECT_TO_GITHUB, exception
      );
    }
    return value;
  }

}
