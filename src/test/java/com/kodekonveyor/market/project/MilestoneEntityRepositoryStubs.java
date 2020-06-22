package com.kodekonveyor.market.project;

import static org.mockito.Mockito.doReturn;

import java.util.Optional;
import java.util.Set;

import com.kodekonveyor.market.tasks.TaskEntityTestData;

public class MilestoneEntityRepositoryStubs {

  public static void
      behaviour(final MilestoneEntityRepository milestoneEntityRepository) {
    doReturn(Optional.of(MilestoneEntityTestData.get()))
        .when(milestoneEntityRepository).findById(MilestoneTestData.ID);
    doReturn(Set.of(MilestoneEntityTestData.get())).when(
        milestoneEntityRepository
    ).findAllById(Set.of(MilestoneTestData.ID));
    doReturn(Optional.of(MilestoneEntityTestData.get())).when(
        milestoneEntityRepository
    ).findByTask(TaskEntityTestData.getTaskWithStatusUpdated());

    doReturn(Optional.of(MilestoneEntityTestData.getOtherMilestone())).when(
        milestoneEntityRepository
    ).findByTask(TaskEntityTestData.getAssignedTask());
  }

}
