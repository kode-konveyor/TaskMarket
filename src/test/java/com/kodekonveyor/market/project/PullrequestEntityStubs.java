package com.kodekonveyor.market.project;

import static org.mockito.Mockito.doReturn;

import java.util.Optional;

public class PullrequestEntityStubs {

  public static void
      behaviour(final PullRequestEntityRepository pullrequestEntityRepository) {
    doReturn(Optional.of(PullRequestEntityTestData.get()))
        .when(pullrequestEntityRepository).findById(PullrequestTestData.ID);
  }

}
