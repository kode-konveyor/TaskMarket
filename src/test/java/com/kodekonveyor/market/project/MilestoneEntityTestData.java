package com.kodekonveyor.market.project;

import java.util.Set;

import javax.annotation.Generated;

import com.kodekonveyor.market.tasks.TaskEntityTestData;

@Generated("by zenta-tools")
public class MilestoneEntityTestData {

  public final static MilestoneEntity get() {
    final MilestoneEntity milestoneEntity = new MilestoneEntity();
    milestoneEntity.setId(MilestoneTestData.ID);
    milestoneEntity.setTask(Set.of(TaskEntityTestData.get()));
    milestoneEntity.setName(MilestoneTestData.NAME);
    milestoneEntity.setPriority(MilestoneTestData.PRIORITY);
    milestoneEntity.setIsActive(MilestoneTestData.IS_ACTIVE);
    return milestoneEntity;
  };

  public final static MilestoneEntity getOtherMilestone() {

    final MilestoneEntity milestoneEntity = get();
    milestoneEntity.setTask(Set.of(TaskEntityTestData.getAssignedTask()));
    return milestoneEntity;

  }

  public static MilestoneEntity getUpForGrab() {
    final MilestoneEntity milestoneEntity = get();
    milestoneEntity.setTask(
        Set.of(
            TaskEntityTestData.getUpForGrab(),
            TaskEntityTestData.getPrivateProjectInProgressTask()
        )
    );
    return milestoneEntity;
  }

  public static MilestoneEntity getTasksMoreThanMinimumForGrab() {
    final MilestoneEntity milestoneEntity = get();
    milestoneEntity.setTask(
        Set.of(
            TaskEntityTestData.get(), TaskEntityTestData.getTaskOne(),
            TaskEntityTestData.getTaskTwo()
        )
    );
    return milestoneEntity;
  }

  public static MilestoneEntity getTasksEqualToMinimumForGrab() {
    final MilestoneEntity milestoneEntity = get();
    milestoneEntity.setTask(
        Set.of(
            TaskEntityTestData.get(), TaskEntityTestData.getTaskOne(),
            TaskEntityTestData.getInProgressTask()
        )
    );
    return milestoneEntity;
  }

  public static Object getNoTasksMilestone() {
    final MilestoneEntity milestoneEntity = get();
    milestoneEntity.setTask(Set.of());
    return milestoneEntity;
  }

  public static MilestoneEntity getMilestoneTwo() {
    final MilestoneEntity milestoneEntity = getTasksEqualToMinimumForGrab();
    milestoneEntity.setId(MilestoneTestData.ID_1);
    return milestoneEntity;
  }

}
