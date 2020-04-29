package com.kodekonveyor.market.tasks;

import com.kodekonveyor.market.register.MarketUserEntityTestData;

public class TaskDToTestData {

  public static TaskDTO get() {
    final TaskDTO dto = new TaskDTO();
    dto.setGithubId(TaskEntityTestData.ISSUE_ID);
    dto.setName(TaskEntityTestData.ISSUE_NAME);
    dto.setProject(GetRepositoryTasksServiceTestData.REPO_NAME);
    dto.setResponsible(MarketUserEntityTestData.get().getLogin().getLogin());
    dto.setStatus(TaskStatusEnum.UP_FOR_GRAB);

    return dto;
  }

}
