package com.kodekonveyor.market.tasks;

public class TaskEntityStatusesTestData {

  public static TaskEntity getStatusGrabbedOverThreeDays() {
    final TaskEntity taskEntity = TaskEntityTestData.get();
    taskEntity.setStatus(TaskStatusEnum.IN_PROGRESS);
    taskEntity.setGrabDate(TaskTestData.DATE_OLDER_THAN_THREE_DAYS);
    return taskEntity;
  }

  public static TaskEntity getStatusUngrabbed() {
    final TaskEntity taskEntity = TaskEntityTestData.get();
    taskEntity.setGrabDate(null);
    taskEntity.setMarketUser(null);
    taskEntity.setStatus(TaskStatusEnum.UP_FOR_GRAB);
    return taskEntity;
  }

  public static TaskEntity getStatusGrabbedExactlyThreeDays() {
    final TaskEntity taskEntity = TaskEntityTestData.get();
    taskEntity.setStatus(TaskStatusEnum.IN_PROGRESS);
    taskEntity.setGrabDate(TaskTestData.DATE_THREE_DAYS_SINCE_GRABBED);
    return taskEntity;
  }

  public static TaskEntity getStatusGrabbedForFourDays() {
    final TaskEntity taskEntity = TaskEntityTestData.get();
    taskEntity.setStatus(TaskStatusEnum.IN_PROGRESS);
    taskEntity.setGrabDate(TaskTestData.DATE_FOUR_DAYS_SINCE_GRABBED);
    return taskEntity;
  }

}
