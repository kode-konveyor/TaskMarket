package com.kodekonveyor.authentication;

import static org.mockito.Mockito.doReturn;

public class AuthenticatedUserStubs {

  public static void
      authenticated(final AuthenticatedUserService authenticatedUserService) {
    doReturn(UserEntityTestData.get()).when(authenticatedUserService)
        .call();
  }

  public static void
      canBePayed(final AuthenticatedUserService authenticatedUserService) {
    doReturn(UserEntityTestData.getRoleCanbePayed())
        .when(authenticatedUserService)
        .call();
  }

  public static void
      manager(final AuthenticatedUserService authenticatedUserService) {
    doReturn(UserEntityTestData.getRolemanager())
        .when(authenticatedUserService)
        .call();

  }

  public static void noMarketuser(
      final AuthenticatedUserService authenticatedUserService
  ) {
    doReturn(UserEntityTestData.getLoginNoMarket())
        .when(authenticatedUserService)
        .call();
  }

  public static void
      project(final AuthenticatedUserService authenticatedUserService) {
    doReturn(UserEntityTestData.getRoleProject())
        .when(authenticatedUserService)
        .call();
  }

  public static void
      registered(final AuthenticatedUserService authenticatedUserService) {
    doReturn(UserEntityTestData.getRoleRegistered())
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
