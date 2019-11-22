package com.kodekonveyor.market;

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

@ControllerAdvice
@InterfaceClass
@ExcludeFromCodeCoverage("interface to underlaying framework")
public class RestResponseEntityExceptionHandler
    extends ResponseEntityExceptionHandler {

  @Autowired
  private LoggerService loggerService;

  @ExceptionHandler({
      NotLoggedInException.class,
      UnauthorizedException.class
  })
  public ResponseEntity<Object> handleNotLoggedInException(
      final RuntimeException exception, final WebRequest request
  ) {
    final StackTraceElement location = exception.getStackTrace()[0];
    final String message =
        exception.getClass().getSimpleName() + ":" + exception.getMessage() +
            " at " +
            location.getFileName() + ":" +
            location.getLineNumber();
    loggerService
        .call(
            "exception", LogSeverityEnum.ERROR,
            message
        );

    final String bodyOfResponse = exception.getMessage();
    final HttpHeaders headers = new HttpHeaders();
    return handleExceptionInternal(
        exception, bodyOfResponse,
        headers, HttpStatus.UNAUTHORIZED, request
    );
  }
}
