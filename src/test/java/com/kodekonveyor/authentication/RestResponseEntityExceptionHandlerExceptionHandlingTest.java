package com.kodekonveyor.authentication;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.web.context.request.WebRequest;

import com.kodekonveyor.annotations.TestedBehaviour;
import com.kodekonveyor.annotations.TestedService;
import com.kodekonveyor.logging.LoggingMarkerConstants;
import com.kodekonveyor.market.UnauthorizedException;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("exception handling")
@TestedService("RestResponseEntityExceptionHandler")

public class RestResponseEntityExceptionHandlerExceptionHandlingTest
    extends RestResponseEntityExceptionHandlerTestBase {

  @DisplayName("if a NotLoggedInException is thrown, logs 'not logged in'")
  @Test
  public void test() {
    final NotLoggedInException exception =
        new NotLoggedInException(
            RestResponseEntityExceptionHandlerTestData.NOT_LOGGED_IN
        );
    final WebRequest request = mock(WebRequest.class);
    restResponseEntityExceptionHandler
        .handleNotLoggedInException(exception, request);
    verify(loggerService)
        .warn(
            eq(LoggingMarkerConstants.AUTHENTICATION), ArgumentMatchers.contains(RestResponseEntityExceptionHandlerTestData.NOT_LOGGED_IN)
        );
  }

  @DisplayName(
    "if a UnauthorizedException is thrown, logs 'unauthorized'"
  )
  @Test
  public void test1() {
    final UnauthorizedException exception =
        new UnauthorizedException(
            RestResponseEntityExceptionHandlerTestData.MESSAGE
        );
    final WebRequest request = mock(WebRequest.class);
    restResponseEntityExceptionHandler
        .handleNotLoggedInException(exception, request);
    verify(loggerService)
        .warn(
            eq(LoggingMarkerConstants.AUTHENTICATION), ArgumentMatchers.contains(RestResponseEntityExceptionHandlerTestData.UNAUTHORIZED)
        );
  }

}
