package com.kodekonveyor.market.github;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.kodekonveyor.annotations.ExcludeFromCodeCoverage;
import com.kodekonveyor.market.LogSeverityEnum;
import com.kodekonveyor.market.LoggerService;
import com.kodekonveyor.market.proxies.ObjectMapperService;

@Service
@ExcludeFromCodeCoverage("interface to underlying framework")
public class GithubGetService {

  @Autowired
  private LoggerService loggerService;

  @Autowired
  private ObjectMapperService objectMapperProxy;

  public <ValueType> ValueType
      call(final String command, final Class<ValueType> cls) {
    final String uri = "https://api.github.com" + command;
    loggerService.call("githubCall", LogSeverityEnum.DEBUG, uri);
    URL url;
    try {
      url = new URL(uri);
    } catch (final MalformedURLException exception) {
      throw new ResponseStatusException(
          HttpStatus.INTERNAL_SERVER_ERROR, "internal error", exception
      );
    }

    objectMapperProxy
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    final ValueType value;
    try {
      value = objectMapperProxy.readValue(url, cls);
    } catch (final IOException exception) {
      throw new ResponseStatusException(
          HttpStatus.SERVICE_UNAVAILABLE, "cannot connect to github", exception
      );
    }
    return value;
  }

}
