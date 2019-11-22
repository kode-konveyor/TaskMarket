package com.kodekonveyor.authentication;

import org.junit.jupiter.api.BeforeEach;

public class RemoteAuthenticationTestBase {

  RemoteAuthentication auth;
  UserTestData testData;

  @BeforeEach
  public void setUp() {
    testData = new UserTestData();
    auth = new RemoteAuthentication(testData.TEST_USER_ENTITY);
  }

}
