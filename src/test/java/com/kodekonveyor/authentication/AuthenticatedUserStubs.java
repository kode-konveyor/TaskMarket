package com.kodekonveyor.authentication;

import static org.mockito.Mockito.doReturn;

public class AuthenticatedUserStubs {

  public static void
      authenticated(final AuthenticatedUserService authenticatedUserService) {
    doReturn(UserEntityTestData.get()).when(authenticatedUserService)
        .call();
  }

  public static void noMarketuser(
      final AuthenticatedUserService authenticatedUserService
  ) {
    doReturn(UserEntityTestData.getLoginNoMarket())
        .when(authenticatedUserService)
        .call();
  }

  public static void salesUser(
      final AuthenticatedUserService authenticatedUserService
  ) {
    doReturn(UserEntityTestData.getRoleSales()).when(authenticatedUserService)
        .call();
  }
}
