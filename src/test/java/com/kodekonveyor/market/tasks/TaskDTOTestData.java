package com.kodekonveyor.market.tasks;

import com.kodekonveyor.authentication.UserEntityTestData;
import com.kodekonveyor.market.project.ProjectEntityTestData;

public class TaskDTOTestData {

  public static final TaskDTO get() {
    final TaskDTO taskDTO = new TaskDTO();
    taskDTO.setGithubId(TaskEntityTestData.ISSUE_ID);
    taskDTO.setName(TaskEntityTestData.ISSUE_NAME);
    taskDTO.setProject(ProjectEntityTestData.NAME);
    taskDTO
        .setResponsible(UserEntityTestData.LOGIN);
    return taskDTO;
  }

}
