package com.kodekonveyor.market.tasks;

import static org.mockito.Mockito.doReturn;

import java.util.List;
import java.util.Optional;

import org.mockito.Mockito;

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
    doReturn(List.of(TaskEntityTestData.get()))
        .when(taskEntityRepository).findByServiceAndBehaviour(TaskTestData.SERVICE, TaskTestData.BEHAVIOUR);
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

  public static void
      delimiterDescription(final TaskEntityRepository taskEntityRepository) {
    Mockito.doReturn(List.of(TaskEntityTestData.getTaskUpdatedDescrition()))
        .when(taskEntityRepository).findByServiceAndBehaviour(TaskTestData.SERVICE, TaskTestData.BEHAVIOUR);

  }

  public static void
      taskNotinRepository(final TaskEntityRepository taskEntityRepository) {
    Mockito.doReturn(List.of())
        .when(taskEntityRepository).findByServiceAndBehaviour(TaskTestData.SERVICE, TaskTestData.BEHAVIOUR);
    Mockito.doReturn(List.of())
        .when(taskEntityRepository).findByServiceAndBehaviour(TaskTestData.OTHER_SERVICE, TaskTestData.BEHAVIOUR);
    Mockito.doReturn(List.of())
        .when(taskEntityRepository).findByServiceAndBehaviour(TaskTestData.SERVICE, TaskTestData.OTHER_BEHAVIOUR);

  }

}
