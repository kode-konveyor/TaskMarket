package com.kodekonveyor.market.tasks;

import java.util.List;
import java.util.Optional;

import org.mockito.Mockito;

public class TaskEntityRepositoryStubs {

  public static void
      grabbedOverThreeDays(final TaskEntityRepository taskEntityRepository) {
    Mockito.doReturn(List.of(TaskEntityTestData.getGrabbedOverThreeDays()))
        .when(taskEntityRepository).findByStatus(TaskStatusEnum.IN_PROGRESS);

  }

  public static void
      grabbedExactlyThreeDays(final TaskEntityRepository taskEntityRepository) {
    Mockito.doReturn(List.of(TaskEntityTestData.getGrabbedExactlyThreeDays()))
        .when(taskEntityRepository).findByStatus(TaskStatusEnum.IN_PROGRESS);

  }

  public static void
      grabbedForFourDays(final TaskEntityRepository taskEntityRepository) {
    Mockito.doReturn(List.of(TaskEntityTestData.getGrabbedForFourDays()))
        .when(taskEntityRepository).findByStatus(TaskStatusEnum.IN_PROGRESS);

  }

  public static void
      behaviour(final TaskEntityRepository taskEntityRepository) {
    Mockito.doReturn(Optional.of(TaskEntityTestData.getPullRequestIssuedTask()))
        .when(taskEntityRepository).findById(TaskTestData.ID);
  }

}
