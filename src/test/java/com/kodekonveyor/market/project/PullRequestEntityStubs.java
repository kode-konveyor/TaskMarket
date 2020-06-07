package com.kodekonveyor.market.project;

import static org.mockito.Mockito.doReturn;

import java.util.List;
import java.util.Optional;

import org.mockito.Mockito;

import com.kodekonveyor.market.tasks.TaskEntityTestData;

public class PullRequestEntityStubs {

  public static void
      behaviour(final PullrequestEntityRepository pullrequestEntityRepository) {
    doReturn(Optional.of(PullrequestEntityTestData.get()))
        .when(pullrequestEntityRepository).findById(PullRequestTestData.ID);
  }

  public static void
      pullRequestNotIssued(
          final PullrequestEntityRepository pullrequestEntityRepository
      ) {
    doReturn(List.of())
        .when(pullrequestEntityRepository).findByTask(TaskEntityTestData.getGrabbedOverThreeDays());
    doReturn(List.of())
        .when(pullrequestEntityRepository).findByTask(TaskEntityTestData.getGrabbedExactlyThreeDays());
    doReturn(List.of())
        .when(pullrequestEntityRepository).findByTask(TaskEntityTestData.getGrabbedForFourDays());
  }

  public static void PullrequestIssued(
      final PullrequestEntityRepository pullrequestEntityRepository
  ) {
    doReturn(List.of(PullrequestEntityTestData.getGrabbedOverThreeDays()))
        .when(pullrequestEntityRepository).findByTask(TaskEntityTestData.getGrabbedOverThreeDays());
    doReturn(List.of(PullrequestEntityTestData.getGrabbedExactlyThreeDays()))
        .when(pullrequestEntityRepository).findByTask(TaskEntityTestData.getGrabbedExactlyThreeDays());
    doReturn(List.of(PullrequestEntityTestData.getGrabbedForFourDays()))
        .when(pullrequestEntityRepository).findByTask(TaskEntityTestData.getGrabbedForFourDays());

  }

  public static void
      allPullRequests(
          final PullrequestEntityRepository pullrequestEntityRepository
      ) {
    Mockito.doReturn(
        List.of(PullrequestEntityTestData.getPullRequestTask())
    ).when(pullrequestEntityRepository).findAll();

  }

}
