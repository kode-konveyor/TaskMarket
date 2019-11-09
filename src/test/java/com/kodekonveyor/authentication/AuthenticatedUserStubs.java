package com.kodekonveyor.authentication;

import static org.mockito.Mockito.doReturn;

public class AuthenticatedUserStubs {

  public static void authenticated(
      final AuthenticatedUserService authenticatedUserService,
      final UserTestData userTestData
  ) {
    doReturn(userTestData.USER).when(authenticatedUserService).call();
  }

  public static void salesUser(
      final AuthenticatedUserService authenticatedUserService,
      final UserTestData userTestData
  ) {
    doReturn(userTestData.SALES_USER).when(authenticatedUserService).call();
  }

}
