package com.kodekonveyor.market.tasks;

import javax.annotation.Generated;

import com.kodekonveyor.market.register.MarketUserEntityTestData;

@Generated("by zenta-tools")
public class TaskEntityTestData {

  public final static TaskEntity get() {
    final TaskEntity taskEntity = new TaskEntity();
    taskEntity.setId(TaskTestData.ID);
    taskEntity.setMarketUser(MarketUserEntityTestData.get());
    taskEntity.setService(TaskTestData.SERVICE);
    taskEntity.setBehaviour(TaskTestData.BEHAVIOUR);
    taskEntity.setGithubId(TaskTestData.GITHUB_ID);
    taskEntity.setDescription(TaskTestData.DESCRIPTION);

    return taskEntity;
  };

}
