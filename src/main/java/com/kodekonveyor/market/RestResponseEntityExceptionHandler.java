package com.kodekonveyor.market;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler
    extends ResponseEntityExceptionHandler {

  @Autowired
  LoggerService loggerService;

  @ExceptionHandler(value = {
      NotLoggedInException.class
  })
  protected ResponseEntity<Object> handleNotLoggedInException(
      final NotLoggedInException ex, final WebRequest request
  ) {
    final String bodyOfResponse = ex.getMessage();

    loggerService.call("not logged in");
    final HttpHeaders headers = new HttpHeaders();
    headers.add("Location", ex.getRedirectUri());
    return handleExceptionInternal(
        ex, bodyOfResponse,
        headers, HttpStatus.FOUND, request
    );
  }
}
