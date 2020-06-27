package com.kodekonveyor.market.tasks;

public class TaskEntityDescriptionsTestData {

  public static TaskEntity getDescritionUpdated() {
    final TaskEntity taskEntity = TaskEntityTestData.get();
    taskEntity.setDescription(
        TaskTestData.TASK_DESCRIPTION_START + TaskTestData.DESCRIPTION +
            TaskTestData.TASK_DESCRIPTION_END
    );
    return taskEntity;
  }

  public static TaskEntity getDescriptionDifferentUpdated() {
    final TaskEntity taskEntity = TaskEntityTestData.get();
    taskEntity.setDescription(
        TaskTestData.TASK_DESCRIPTION_START + TaskTestData.DESCRIPTION +
            TaskTestData.TASK_DESCRIPTION_END + TaskTestData.DIFF +
            TaskTestData.DIFFERENT_DESCRIPTION
    );
    return taskEntity;
  }

  public static TaskEntity getDescriptionDifferent() {
    final TaskEntity taskEntity = TaskEntityTestData.get();
    taskEntity.setDescription(
        TaskTestData.DESCRIPTION + TaskTestData.DIFFERENT_DESCRIPTION
    );
    return taskEntity;
  }

  public static TaskEntity getDescritionUpdatedDifferentService() {
    final TaskEntity taskEntity = TaskEntityTestData.getServiceDifferent();
    taskEntity.setDescription(
        TaskTestData.TASK_DESCRIPTION_START + TaskTestData.DESCRIPTION +
            TaskTestData.TASK_DESCRIPTION_END
    );
    taskEntity.setStatus(TaskStatusEnum.NOT_IN_MODEL);
    return taskEntity;
  }

  public static TaskEntity getDescritionUpdatedDifferentBehaviour() {
    final TaskEntity taskEntity = TaskEntityTestData.getBehaviourDifferent();
    taskEntity.setDescription(
        TaskTestData.TASK_DESCRIPTION_START + TaskTestData.DESCRIPTION +
            TaskTestData.TASK_DESCRIPTION_END
    );
    taskEntity.setStatus(TaskStatusEnum.NOT_IN_MODEL);
    return taskEntity;
  }

  public static TaskEntity getDescriptionUpdatedNotInModel() {
    final TaskEntity taskEntity = getDescritionUpdated();
    taskEntity.setStatus(TaskStatusEnum.NOT_IN_MODEL);
    return taskEntity;
  }

  public static TaskEntity getDescritionUpdatedDelimiterNotAtStart() {
    final TaskEntity taskEntity = TaskEntityTestData.get();
    taskEntity.setDescription(
        TaskTestData.TEXT_BEFORE_DELIMITER +
            TaskTestData.TASK_DESCRIPTION_START + TaskTestData.DESCRIPTION +
            TaskTestData.TASK_DESCRIPTION_END
    );
    return taskEntity;
  }

  public static TaskEntity getDescriptionUpdatedDelimiterAtStart() {
    final TaskEntity taskEntity = TaskEntityTestData.get();
    taskEntity.setDescription(
        TaskTestData.TASK_DESCRIPTION_START +
            TaskTestData.TEXT_BEFORE_DELIMITER + TaskTestData.DESCRIPTION +
            TaskTestData.TASK_DESCRIPTION_END + TaskTestData.DIFF +
            TaskTestData.DIFFERENT_DESCRIPTION
    );
    return taskEntity;
  }

  public static TaskEntity getDescriptionDelimiterNotAtStart() {
    final TaskEntity taskEntity = TaskEntityTestData.get();
    taskEntity.setDescription(
        TaskTestData.TEXT_BEFORE_DELIMITER + TaskTestData.DESCRIPTION +
            TaskTestData.DIFFERENT_DESCRIPTION
    );
    return taskEntity;
  }

  public static TaskEntity getDescritionUpdatedNoEndDelimiter() {
    final TaskEntity taskEntity = TaskEntityTestData.get();
    taskEntity.setDescription(
        TaskTestData.TASK_DESCRIPTION_START + TaskTestData.DESCRIPTION
    );
    return taskEntity;
  }

  public static TaskEntity getDescritionUpdatedNoStartDelimiter() {
    final TaskEntity taskEntity = TaskEntityTestData.get();
    taskEntity.setDescription(
        TaskTestData.DESCRIPTION + TaskTestData.TASK_DESCRIPTION_END
    );
    return taskEntity;
  }

}
