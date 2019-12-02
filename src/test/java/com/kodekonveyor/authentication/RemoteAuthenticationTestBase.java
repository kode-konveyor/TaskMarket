package com.kodekonveyor.authentication;

import org.junit.jupiter.api.BeforeEach;

public class RemoteAuthenticationTestBase {

  RemoteAuthentication remoteAuthentication;

  @BeforeEach
  public void setUp() {
    remoteAuthentication = new RemoteAuthentication(UserEntityTestData.get());
  }

}
