package com.kodekonveyor.market.tasks;

import com.kodekonveyor.market.register.MarketUserTestData;

public class TaskDTOTestData {

  public static final TaskDTO getDifferentBehaviour() {
    final TaskDTO taskDTO = get();
    taskDTO.setBehaviour(TaskTestData.DIFFERENT_BEHAVIOUR);
    return taskDTO;
  }

  public final static TaskDTO get() {
    final TaskDTO taskDTO = new TaskDTO();
    taskDTO.setId(TaskTestData.ID);
    taskDTO.setMarketUser(MarketUserTestData.ID);
    taskDTO.setService(TaskTestData.SERVICE);
    taskDTO.setBehaviour(TaskTestData.BEHAVIOUR);
    taskDTO.setGithubId(TaskTestData.GITHUB_ID);
    taskDTO.setDescription(TaskTestData.DESCRIPTION);
    taskDTO.setStatus(TaskStatusEnum.UP_FOR_GRAB);
    return taskDTO;
  }

  public static final TaskDTO getNewDescription() {
    final TaskDTO taskDTO = get();
    taskDTO.setDescription(TaskTestData.NEW_DESCRIPTION);
    return taskDTO;
  }

}
