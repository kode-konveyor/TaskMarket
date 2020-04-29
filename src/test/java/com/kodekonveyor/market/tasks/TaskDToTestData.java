package com.kodekonveyor.market.tasks;

public class TaskDToTestData {

  public static TaskDTO get() {
    final TaskDTO dto = new TaskDTO();
    dto.setId(TaskTestData.ID);
    dto.setService(TaskTestData.SERVICE);
    dto.setBehaviour(TaskTestData.BEHAVIOUR);
    dto.setGithubId(TaskTestData.GITHUB_ID);
    dto.setDescription(TaskTestData.DESCRIPTION);

    return dto;
  }

}
