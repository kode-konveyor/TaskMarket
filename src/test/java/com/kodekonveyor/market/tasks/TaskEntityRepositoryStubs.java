package com.kodekonveyor.market.tasks;

import static org.mockito.Mockito.doReturn;

public class TaskEntityRepositoryStubs {

  public static void
      behaviour(final TaskEntityRepository taskEntityRepository) {

    doReturn(TaskEntityTestData.get()).when(taskEntityRepository)
        .findbyId(TaskTestData.ID);
    doReturn(TaskEntityTestData.getInProgressTask()).when(taskEntityRepository)
        .findbyId(TaskTestData.ID_IN_PROGRESS);
  }

}
