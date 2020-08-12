package com.kodekonveyor.market.project;

import com.kodekonveyor.market.tasks.TaskEntityStatusesTestData;
import com.kodekonveyor.market.tasks.TaskEntityTestData;
import org.mockito.ArgumentCaptor;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;

public class PullRequestEntityStubs {

  public static void
      behaviour(final PullrequestEntityRepository pullrequestEntityRepository) {
    doReturn(Optional.of(PullrequestEntityTestData.get()))
        .when(pullrequestEntityRepository).findById(PullRequestTestData.ID);
    doReturn(
        List.of(PullrequestEntityTestData.getPullRequestTask())
    ).when(pullrequestEntityRepository).findAll();

  }

  public static void
      pullRequestNotIssued(
          final PullrequestEntityRepository pullrequestEntityRepository
      ) {
    doReturn(List.of())
        .when(pullrequestEntityRepository).findByTask(TaskEntityStatusesTestData.getStatusGrabbedOverThreeDays());
    doReturn(List.of())
        .when(pullrequestEntityRepository).findByTask(TaskEntityStatusesTestData.getStatusGrabbedExactlyThreeDays());
    doReturn(List.of())
        .when(pullrequestEntityRepository).findByTask(TaskEntityStatusesTestData.getStatusGrabbedForFourDays());
    doReturn(List.of())
        .when(pullrequestEntityRepository).findByTask(TaskEntityTestData.get());
  }

  public static void pullRequestIssued(
      final PullrequestEntityRepository pullrequestEntityRepository
  ) {
    doReturn(List.of(PullrequestEntityTestData.getGrabbedOverThreeDays()))
        .when(pullrequestEntityRepository).findByTask(TaskEntityStatusesTestData.getStatusGrabbedOverThreeDays());
    doReturn(List.of(PullrequestEntityTestData.getGrabbedExactlyThreeDays()))
        .when(pullrequestEntityRepository).findByTask(TaskEntityStatusesTestData.getStatusGrabbedExactlyThreeDays());
    doReturn(List.of(PullrequestEntityTestData.getGrabbedForFourDays()))
        .when(pullrequestEntityRepository).findByTask(TaskEntityStatusesTestData.getStatusGrabbedForFourDays());
    doReturn(List.of(PullrequestEntityTestData.getGrabbedExactlyThreeDays()))
        .when(pullrequestEntityRepository).findByTask(TaskEntityTestData.get());

  }

  public static void behaviourSaveAndCaptureArgs(
          final PullrequestEntityRepository pullrequestEntityRepository,
          final ArgumentCaptor<PullRequestEntity> captor
  ) {
    doAnswer(inv -> inv.getArgument(0))
            .when(pullrequestEntityRepository)
            .save(captor.capture());
  }

}
