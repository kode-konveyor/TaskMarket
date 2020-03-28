package com.kodekonveyor.market.tasks;

import com.kodekonveyor.market.project.ProjectEntityTestData;
import com.kodekonveyor.market.register.MarketUserEntityTestData;

public class TaskEntityTestData {

  public static final String ISSUE_ID = "4422";
  public static final String ISSUE_NAME = "SomeController/behaviour";

  public static TaskEntity get() {
    final TaskEntity entity = new TaskEntity();
    entity.setGithubId(ISSUE_ID);
    entity.setName(ISSUE_NAME);
    entity.setProject(ProjectEntityTestData.get());
    entity.setResponsible(MarketUserEntityTestData.get());
    entity.setStatus(TaskStatusEnum.UP_FOR_GRAB);
    return entity;
  }

}
