package com.kodekonveyor.authentication;

import org.junit.jupiter.api.BeforeEach;

public class NotLoggedInExceptionTestBase {

  NotLoggedInException exception;

  @BeforeEach
  public void setUp() {
    exception = new NotLoggedInException(NotLoggedInExceptionTestData.MESSAGE);
  }

}
