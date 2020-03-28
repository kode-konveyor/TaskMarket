package com.kodekonveyor.market.project;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.reset;

import com.kodekonveyor.market.tasks.TaskDToTestData;

public class ProjectEntityStubs {

  public static void behaviour(
      final ProjectEntityRepository projectEntityRepository
  ) {
    reset(projectEntityRepository);
    doReturn(ProjectEntityTestData.list()).when(projectEntityRepository)
        .findAll();
    doReturn(ProjectEntityTestData.list()).when(projectEntityRepository)
        .findByName(TaskDToTestData.get().getProject());
  }
}
