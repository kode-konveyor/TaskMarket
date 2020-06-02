package com.kodekonveyor.market.tasks;

import static org.mockito.Mockito.doReturn;

import java.util.Optional;

public class TaskEntityRepositoryStubs {

  public static void
      behaviour(final TaskEntityRepository taskEntityRepository) {

    doReturn(Optional.of(TaskEntityTestData.get())).when(taskEntityRepository)
        .findById(TaskTestData.ID);
    doReturn(Optional.of(TaskEntityTestData.getInProgressTask()))
        .when(taskEntityRepository)
        .findById(TaskTestData.ID_IN_PROGRESS);
  }

}
