package com.kodekonveyor.market.project;

import static org.mockito.Mockito.doReturn;

import java.util.Optional;

public class PullrequestEntityStubs {

  public static void
      behaviour(final PullrequestEntityRepository pullrequestEntityRepository) {
    doReturn(Optional.of(PullrequestEntityTestData.get()))
        .when(pullrequestEntityRepository).findById(PullrequestTestData.ID);
  }

}
