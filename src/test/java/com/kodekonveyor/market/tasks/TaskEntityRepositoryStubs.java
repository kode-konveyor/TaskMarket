package com.kodekonveyor.market.tasks;

import static org.mockito.Mockito.doReturn;

import java.util.List;
import java.util.Optional;

import com.kodekonveyor.market.register.MarketUserEntityTestData;

public class TaskEntityRepositoryStubs {

  public static void
      grabbedOverThreeDays(final TaskEntityRepository taskEntityRepository) {
    doReturn(List.of(TaskEntityTestData.getGrabbedOverThreeDays()))
        .when(taskEntityRepository).findByStatus(TaskStatusEnum.IN_PROGRESS);

  }

  public static void
      grabbedExactlyThreeDays(final TaskEntityRepository taskEntityRepository) {
    doReturn(List.of(TaskEntityTestData.getGrabbedExactlyThreeDays()))
        .when(taskEntityRepository).findByStatus(TaskStatusEnum.IN_PROGRESS);

  }

  public static void
      grabbedForFourDays(final TaskEntityRepository taskEntityRepository) {
    doReturn(List.of(TaskEntityTestData.getGrabbedForFourDays()))
        .when(taskEntityRepository).findByStatus(TaskStatusEnum.IN_PROGRESS);

  }

  public static void
      behaviour(final TaskEntityRepository taskEntityRepository) {
    doReturn(Optional.of(TaskEntityTestData.getPullRequestIssuedTask()))
        .when(taskEntityRepository).findById(TaskTestData.ID);

    doReturn(List.of(TaskEntityTestData.getInProgress())).when(
        taskEntityRepository
    ).findByStatusAndMarketUser(
        TaskStatusEnum.IN_PROGRESS,
        MarketUserEntityTestData.getPrivateProjectCoder()
    );

    doReturn(List.of(TaskEntityTestData.getInProgressPublicProject()))
        .when(
            taskEntityRepository
        ).findByStatusAndMarketUser(
            TaskStatusEnum.IN_PROGRESS,
            MarketUserEntityTestData.get()
        );

    doReturn(List.of(TaskEntityTestData.getUpForGrab())).when(
        taskEntityRepository
    ).findByStatus(TaskStatusEnum.UP_FOR_GRAB);

    doReturn(List.of(TaskEntityTestData.getClosedTask())).when(
        taskEntityRepository
    ).findByStatusAndMarketUser(
        TaskStatusEnum.DONE, MarketUserEntityTestData.getPrivateProjectCoder()
    );

    doReturn(List.of(TaskEntityTestData.getClosedTaskPublicProject()))
        .when(
            taskEntityRepository
        ).findByStatusAndMarketUser(
            TaskStatusEnum.DONE, MarketUserEntityTestData.get()
        );
  }

  public static void
      behaviour2(final TaskEntityRepository taskEntityRepository) {
    doReturn(Optional.of(TaskEntityTestData.get())).when(taskEntityRepository)
        .findById(TaskTestData.ID);
    doReturn(Optional.of(TaskEntityTestData.getInProgressTask()))
        .when(taskEntityRepository)
        .findById(TaskTestData.ID_IN_PROGRESS);
    doReturn(Optional.of(TaskEntityTestData.getUnassignedTask()))
        .when(taskEntityRepository)
        .findById(TaskTestData.ID_2);
  }

}
