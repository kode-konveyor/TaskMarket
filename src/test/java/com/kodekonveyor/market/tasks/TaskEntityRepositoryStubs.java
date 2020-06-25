package com.kodekonveyor.market.tasks;

import static org.mockito.Mockito.doReturn;

import java.util.List;
import java.util.Optional;

public class TaskEntityRepositoryStubs {

  public static void
      grabbedOverThreeDays(final TaskEntityRepository taskEntityRepository) {
    doReturn(List.of(TaskEntityTestData.getStatusGrabbedOverThreeDays()))
        .when(taskEntityRepository).findByStatus(TaskStatusEnum.IN_PROGRESS);

  }

  public static void
      grabbedExactlyThreeDays(final TaskEntityRepository taskEntityRepository) {
    doReturn(List.of(TaskEntityTestData.getStatusGrabbedExactlyThreeDays()))
        .when(taskEntityRepository).findByStatus(TaskStatusEnum.IN_PROGRESS);

  }

  public static void
      grabbedForFourDays(final TaskEntityRepository taskEntityRepository) {
    doReturn(List.of(TaskEntityTestData.getStatusGrabbedForFourDays()))
        .when(taskEntityRepository).findByStatus(TaskStatusEnum.IN_PROGRESS);

  }

  public static void
      behaviour(final TaskEntityRepository taskEntityRepository) {
    doReturn(Optional.of(TaskEntityTestData.getPullRequestIssuedTask()))
        .when(taskEntityRepository).findById(TaskTestData.ID);
    doReturn(Optional.of(TaskEntityTestData.get()))
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
    doReturn(Optional.of(TaskEntityDescriptionsTestData.getDescritionUpdated()))
        .when(taskEntityRepository).findByServiceAndBehaviour(TaskTestData.SERVICE, TaskTestData.BEHAVIOUR);

  }

  public static void
      taskNotinRepository(final TaskEntityRepository taskEntityRepository) {
    doReturn(Optional.empty())
        .when(taskEntityRepository).findByServiceAndBehaviour(TaskTestData.SERVICE, TaskTestData.BEHAVIOUR);
    doReturn(Optional.empty())
        .when(taskEntityRepository).findByServiceAndBehaviour(TaskTestData.OTHER_SERVICE, TaskTestData.BEHAVIOUR);
    doReturn(Optional.empty())
        .when(taskEntityRepository).findByServiceAndBehaviour(TaskTestData.SERVICE, TaskTestData.OTHER_BEHAVIOUR);

  }

  public static void delimiterNotAtStartDesctiptionTask(
      final TaskEntityRepository taskEntityRepository
  ) {
    doReturn(
        Optional
            .of(
                TaskEntityDescriptionsTestData
                    .getDescritionUpdatedDelimiterNotAtStart()
            )
    )
        .when(taskEntityRepository).findByServiceAndBehaviour(TaskTestData.SERVICE, TaskTestData.BEHAVIOUR);

  }

  public static void getupdatedDescriptionNoEndDelimiter(
      final TaskEntityRepository taskEntityRepository
  ) {
    doReturn(
        Optional
            .of(
                TaskEntityDescriptionsTestData
                    .getDescritionUpdatedNoEndDelimiter()
            )
    )
        .when(taskEntityRepository).findByServiceAndBehaviour(TaskTestData.SERVICE, TaskTestData.BEHAVIOUR);

  }

  public static void getupdatedDescriptionNoStartDelimiter(
      final TaskEntityRepository taskEntityRepository
  ) {
    doReturn(
        Optional
            .of(
                TaskEntityDescriptionsTestData
                    .getDescritionUpdatedNoStartDelimiter()
            )
    )
        .when(taskEntityRepository).findByServiceAndBehaviour(TaskTestData.SERVICE, TaskTestData.BEHAVIOUR);

  }

}
