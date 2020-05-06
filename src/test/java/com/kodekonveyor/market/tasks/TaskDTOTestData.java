package com.kodekonveyor.market.tasks;

import javax.annotation.Generated;

import com.kodekonveyor.market.register.MarketUserTestData;

@Generated("by zenta-tools")
public class TaskDTOTestData {

  public final static TaskDTO get() {
    final TaskDTO taskDTO = new TaskDTO();
    taskDTO.setId(TaskTestData.ID);
    taskDTO.setMarketUser(MarketUserTestData.ID);
    taskDTO.setService(TaskTestData.SERVICE);
    taskDTO.setBehaviour(TaskTestData.BEHAVIOUR);
    taskDTO.setGithubId(TaskTestData.GITHUB_ID);
    taskDTO.setDescription(TaskTestData.DESCRIPTION);

    return taskDTO;
  };

}
