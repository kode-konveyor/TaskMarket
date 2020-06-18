package com.kodekonveyor.market.tasks;

import javax.annotation.Generated;

import com.kodekonveyor.market.register.MarketUserEntityTestData;

@Generated("by zenta-tools")
public class TaskEntityTestData { //NOPMD

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
  }

  public static TaskEntity getGrabbedOverThreeDays() {
    final TaskEntity taskEntity = get();
    taskEntity.setStatus(TaskStatusEnum.IN_PROGRESS);
    taskEntity.setGrabDate(TaskTestData.DATE_OLDER_THAN_THREE_DAYS);
    return taskEntity;
  }

  public static TaskEntity getUngrabbedTask() {
    final TaskEntity taskEntity = getGrabbedOverThreeDays();
    taskEntity.setMarketUser(null);
    taskEntity.setStatus(TaskStatusEnum.UP_FOR_GRAB);
    return taskEntity;
  }

  public static TaskEntity getGrabbedExactlyThreeDays() {
    final TaskEntity taskEntity = get();
    taskEntity.setStatus(TaskStatusEnum.IN_PROGRESS);
    taskEntity.setGrabDate(TaskTestData.DATE_THREE_DAYS_SINCE_GRABBED);
    return taskEntity;
  }

  public static TaskEntity getGrabbedForFourDays() {
    final TaskEntity taskEntity = get();
    taskEntity.setStatus(TaskStatusEnum.IN_PROGRESS);
    taskEntity.setGrabDate(TaskTestData.DATE_FOUR_DAYS_SINCE_GRABBED);
    return taskEntity;
  }

  public static TaskEntity getUngrabbedFourDaysTask() {
    final TaskEntity taskEntity = getGrabbedForFourDays();
    taskEntity.setMarketUser(null);
    taskEntity.setStatus(TaskStatusEnum.UP_FOR_GRAB);
    return taskEntity;
  }

  //  public static TaskEntity getUngrabExactlyThreeDaysTask() {
  //    final TaskEntity taskEntity = getGrabbedExactlyThreeDays();
  //    taskEntity.setMarketUser(null);
  //    taskEntity.setStatus(TaskStatusEnum.UP_FOR_GRAB);
  //
  //    return taskEntity;
  //  }

  public static TaskEntity getPullRequestIssuedTask() {
    final TaskEntity taskEntity = get();
    taskEntity.setStatus(TaskStatusEnum.IN_PROGRESS);
    taskEntity.setGrabDate(TaskTestData.DATE_OLDER_THAN_THREE_DAYS);
    return taskEntity;
  }

  public static TaskEntity getUngrabPullRequestIssuedtask() {
    final TaskEntity taskEntity = getPullRequestIssuedTask();
    taskEntity.setMarketUser(null);
    taskEntity.setStatus(TaskStatusEnum.UP_FOR_GRAB);
    return taskEntity;
  }

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

  public static TaskEntity getTaskUpdatedDescrition() {
    final TaskEntity taskEntity = get();
    taskEntity.setDescription(
        TaskTestData.TASK_DESCRIPTION_START + TaskTestData.DESCRIPTION +
            TaskTestData.TASK_DESCRIPTION_END
    );
    return taskEntity;
  }

  public static TaskEntity getDifferentDescriptionUpdated() {
    final TaskEntity taskEntity = get();
    taskEntity.setDescription(
        TaskTestData.TASK_DESCRIPTION_START + TaskTestData.DESCRIPTION +
            TaskTestData.TASK_DESCRIPTION_END + TaskTestData.DIFF +
            TaskTestData.DIFFERENT_DESCRIPTION
    );
    return taskEntity;
  }

  public static TaskEntity getDifferentDescription() {
    final TaskEntity taskEntity = get();
    taskEntity.setDescription(
        TaskTestData.DESCRIPTION + TaskTestData.DIFFERENT_DESCRIPTION
    );
    return taskEntity;
  }

  public static TaskEntity getDifferentServiceTaskUpdatedDescrition() {
    final TaskEntity taskEntity = getDifferentServiceTask();
    taskEntity.setDescription(
        TaskTestData.TASK_DESCRIPTION_START + TaskTestData.DESCRIPTION +
            TaskTestData.TASK_DESCRIPTION_END
    );
    taskEntity.setStatus(TaskStatusEnum.NOT_IN_MODEL);
    return taskEntity;
  }

  public static TaskEntity getDifferentServiceTask() {
    final TaskEntity taskEntity = get();
    taskEntity.setService(TaskTestData.OTHER_SERVICE);
    return taskEntity;
  }

  public static TaskEntity getDifferentBehaviourTask() {
    final TaskEntity taskEntity = get();
    taskEntity.setBehaviour(TaskTestData.OTHER_BEHAVIOUR);
    return taskEntity;
  }

  public static TaskEntity getDifferentBehaviourTaskUpdatedDescrition() {
    final TaskEntity taskEntity = getDifferentBehaviourTask();
    taskEntity.setDescription(
        TaskTestData.TASK_DESCRIPTION_START + TaskTestData.DESCRIPTION +
            TaskTestData.TASK_DESCRIPTION_END
    );
    taskEntity.setStatus(TaskStatusEnum.NOT_IN_MODEL);
    return taskEntity;
  }

  public static TaskEntity getNotInModelTaskUpdatedDescrition() {
    final TaskEntity taskEntity = getTaskUpdatedDescrition();
    taskEntity.setStatus(TaskStatusEnum.NOT_IN_MODEL);
    return taskEntity;
  }
}
