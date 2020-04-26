package com.kodekonveyor.market.project;

import java.util.Set;

import javax.annotation.Generated;

import com.kodekonveyor.market.tasks.TaskEntityTestData;

@Generated("by zenta-tools")
public class ProjectModelEntityTestData {

  public final static ProjectModelEntity get() {
    final ProjectModelEntity projectModelEntity = new ProjectModelEntity();
    projectModelEntity.setId(ProjectModelTestData.ID);
    projectModelEntity.setTask(Set.of(TaskEntityTestData.get()));
    projectModelEntity.setMilestone(Set.of(MilestoneEntityTestData.get()));

    return projectModelEntity;
  };

}
