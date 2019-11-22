package com.kodekonveyor.authentication;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.kodekonveyor.market.LogTestData;
import com.kodekonveyor.market.LoggerService;
import com.kodekonveyor.market.RestResponseEntityExceptionHandler;

public class RestResponseEntityExceptionHandlerTestBase {

  @InjectMocks
  RestResponseEntityExceptionHandler restResponseEntityExceptionHandler;

  @Mock
  LoggerService loggerService;

  WebAppTestData testData;

  LogTestData logTestData;

  @BeforeEach
  public void setUp() {
    testData = new WebAppTestData(new UserTestData());
    logTestData = new LogTestData();
  }

}
