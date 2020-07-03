package com.kodekonveyor.authentication;

import static org.mockito.Mockito.doReturn;

public class AuthenticatedUserService2Stubs {

  public static void authenticatedInNullDatabase(
      final AuthenticatedUserService authenticatedUserService
  ) {
    doReturn(UserEntityTestData.getIdInNullDatabase())
        .when(authenticatedUserService)
        .call();
  }

  public static void forProjectManagerForZeroBalance(
      final AuthenticatedUserService authenticatedUserService
  ) {
    doReturn(UserEntityTestData.getIdForZeroBalanceForProjectManager())
        .when(authenticatedUserService)
        .call();
  }

  public static void
      privateProjectCoder(
          final AuthenticatedUserService authenticatedUserService
      ) {
    doReturn(UserEntityTestData.getPrivateProjectCoder())
        .when(authenticatedUserService)
        .call();
  }

  public static void technicalUser(
      final AuthenticatedUserService authenticatedUserService
  ) {
    doReturn(UserEntityTestData.getRoleTechnical())
        .when(authenticatedUserService)
        .call();
  }

}
