package com.kodekonveyor.market;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.kodekonveyor.annotations.ExcludeFromCodeCoverage;
import com.kodekonveyor.annotations.InterfaceClass;
import com.kodekonveyor.authentication.NotLoggedInException;
import com.kodekonveyor.logging.LoggingMarkerConstants;

@ControllerAdvice
@InterfaceClass
@ExcludeFromCodeCoverage("interface to underlaying framework")
public class RestResponseEntityExceptionHandler
    extends ResponseEntityExceptionHandler {

  @Autowired
  private Logger loggerService;

  @ExceptionHandler({
      NotLoggedInException.class,
      UnauthorizedException.class,
      ValidationException.class
  })

  public ResponseEntity<Object> handleNotLoggedInException(
      final RuntimeException exception, final WebRequest request
  ) {
    final StackTraceElement location = exception.getStackTrace()[0];
    final String message = String.format(
        MarketConstants.NOT_LOGGED_IN_MESSAGE_TEMPLATE,
        exception.getClass().getSimpleName(),
        exception.getMessage(),
        location.getFileName(),
        location.getLineNumber()
    );
    loggerService
        .warn(
            LoggingMarkerConstants.AUTHENTICATION, message
        );

    final String bodyOfResponse = exception.getMessage();
    final HttpHeaders headers = new HttpHeaders();
    return handleExceptionInternal(
        exception, bodyOfResponse,
        headers, HttpStatus.UNAUTHORIZED, request
    );
  }
}
