package com.kodekonveyor.market.tasks;

<<<<<<< HEAD
import javax.annotation.Generated;
=======
import com.kodekonveyor.authentication.UserEntityTestData;
import com.kodekonveyor.market.project.ProjectEntityTestData;
>>>>>>> origin/develop

import com.kodekonveyor.market.project.MilestoneTestData;

@Generated("by zenta-tools")
public class TaskDTOTestData {

  public final static TaskDTO get() {
    final TaskDTO taskDTO = new TaskDTO();
    taskDTO.setId(TaskTestData.ID);
    taskDTO.setMilestone(MilestoneTestData.ID);
    taskDTO.setService(TaskTestData.SERVICE);
    taskDTO.setBehaviour(TaskTestData.BEHAVIOUR);
    taskDTO.setGithubId(TaskTestData.GITHUB_ID);
    taskDTO.setDescription(TaskTestData.DESCRIPTION);

    return taskDTO;
  };

}
