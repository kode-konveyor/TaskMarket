package com.kodekonveyor.market.project;

import java.util.Set;

import javax.annotation.Generated;

import com.kodekonveyor.market.tasks.TaskTestData;

@Generated("by zenta-tools")
public class ProjectModelDTOTestData {

  public final static ProjectModelDTO get() {
    final ProjectModelDTO projectModelDTO = new ProjectModelDTO();
    projectModelDTO.setId(ProjectModelTestData.ID);
    projectModelDTO.setTask(Set.of(TaskTestData.ID));
    projectModelDTO.setMilestone(Set.of(MilestoneTestData.ID));

    return projectModelDTO;
  };

}
