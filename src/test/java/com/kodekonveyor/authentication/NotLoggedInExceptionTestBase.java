package com.kodekonveyor.authentication;

import org.junit.jupiter.api.BeforeEach;

import com.kodekonveyor.market.LogTestData;

public class NotLoggedInExceptionTestBase {

  NotLoggedInException exception;
  LogTestData logTestData;

  @BeforeEach
  public void setUp() {
    logTestData = new LogTestData();
    exception = new NotLoggedInException(logTestData.MESSAGE);
  }

}
