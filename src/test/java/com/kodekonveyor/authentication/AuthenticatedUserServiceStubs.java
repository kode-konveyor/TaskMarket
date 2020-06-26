package com.kodekonveyor.authentication;

import static org.mockito.Mockito.doReturn;

public class AuthenticatedUserServiceStubs {

  public static void
      authenticated(final AuthenticatedUserService authenticatedUserService) {
    doReturn(UserEntityTestData.get()).when(authenticatedUserService)
        .call();
  }

  public static void
      unregistered(final AuthenticatedUserService authenticatedUserService) {
    doReturn(UserEntityTestData.getIdNoMarketUser())
        .when(authenticatedUserService)
        .call();

  }

  public static void contractTermsNotAccepted(
      final AuthenticatedUserService authenticatedUserService
  ) {
    doReturn(UserEntityTestData.getContractTermsNotAccepted())
        .when(authenticatedUserService)
        .call();
  }

  public static void
      registered(final AuthenticatedUserService authenticatedUserService) {
    doReturn(UserEntityTestData.getRoleCanbePaid())
        .when(authenticatedUserService)
        .call();
  }

  public static void
      kodekonveyorContract(
          final AuthenticatedUserService authenticatedUserService
      ) {
    doReturn(UserEntityTestData.getRoleKodekonveyorContract())
        .when(authenticatedUserService)
        .call();
  }

  public static void
      projectManager(final AuthenticatedUserService authenticatedUserService) {
    doReturn(UserEntityTestData.getRoleProjectManager())
        .when(authenticatedUserService)
        .call();

  }

  public static void salesUser(
      final AuthenticatedUserService authenticatedUserService
  ) {
    doReturn(UserEntityTestData.getRoleSales()).when(authenticatedUserService)
        .call();
  }

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

  public static void technicalUser(
          final AuthenticatedUserService authenticatedUserService
  ) {
    doReturn(UserEntityTestData.getRoleTechnical())
            .when(authenticatedUserService)
            .call();
  }
}
