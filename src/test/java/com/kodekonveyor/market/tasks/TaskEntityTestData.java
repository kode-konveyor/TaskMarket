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
    taskEntity.setStatus(TaskStatusEnum.UP_FOR_GRAB);
    return taskEntity;
  }

  public static TaskEntity getOtherTask() {
    final TaskEntity taskEntity = new TaskEntity();
    taskEntity.setId(TaskTestData.ID_1198);
    taskEntity.setMarketUser(MarketUserEntityTestData.get());
    taskEntity.setService(TaskTestData.SERVICE);
    taskEntity.setBehaviour(TaskTestData.BEHAVIOUR);
    taskEntity.setGithubId(TaskTestData.GITHUB_ID_67);
    taskEntity.setDescription(TaskTestData.DESCRIPTION);
    taskEntity.setStatus(TaskStatusEnum.OPEN);
    return taskEntity;
  };

  public static TaskEntity getTaskWithStatusUpdated() {
    final TaskEntity taskEntity = get();
    taskEntity.setStatus(TaskStatusEnum.IN_PROGRESS);
    return taskEntity;
  }

  public static TaskEntity getInProgressTask() {
    final TaskEntity taskEntity = get();
    taskEntity.setId(TaskTestData.ID_IN_PROGRESS);
    taskEntity.setStatus(TaskStatusEnum.IN_PROGRESS);
    return taskEntity;
  }

  public static TaskEntity getUnassignedTask() {
    final TaskEntity taskEntity = get();
    taskEntity.setId(TaskTestData.ID_2);
    taskEntity.setMarketUser(null);
    return taskEntity;
  }

  public static TaskEntity getAssignedTask() {
    final TaskEntity taskEntity = get();
    taskEntity.setId(TaskTestData.ID_2);
    taskEntity.setMarketUser(MarketUserEntityTestData.get());
    taskEntity.setStatus(TaskStatusEnum.IN_PROGRESS);
    return taskEntity;
  }
}
