package com.kodekonveyor.authentication;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.web.context.request.WebRequest;

import com.kodekonveyor.annotations.TestedBehaviour;
import com.kodekonveyor.annotations.TestedService;
import com.kodekonveyor.market.LoggerService;
import com.kodekonveyor.market.NotLoggedInException;
import com.kodekonveyor.market.RestResponseEntityExceptionHandler;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("Handles NotLoggedInException")
@TestedService("RestResponseEntityExceptionHandler")

public class RestResponseEntityExceptionHandlerTest {

  @InjectMocks
  private RestResponseEntityExceptionHandler restResponseEntityExceptionHandler;

  @Mock
  private LoggerService loggerService;

  private WebappTestData testData;

  @BeforeEach
  public void setUp() {
    final UserTestData userTestData = new UserTestData();
    testData = new WebappTestData(userTestData);
    final NotLoggedInException exception = mock(NotLoggedInException.class);
    final WebRequest request = mock(WebRequest.class);
    restResponseEntityExceptionHandler
        .handleNotLoggedInException(exception, request);
  }

  @DisplayName("logs 'not logged in'")
  @Test
  public void test() {
    verify(loggerService).call(testData.NOT_LOGGED_IN);
  }

}
