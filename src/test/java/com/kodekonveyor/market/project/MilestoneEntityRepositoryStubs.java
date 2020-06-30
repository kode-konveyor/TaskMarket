package com.kodekonveyor.market.project;

import static org.mockito.Mockito.doReturn;

import java.util.List;
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

    doReturn(Optional.of(MilestoneEntityTestData.get())).when(
        milestoneEntityRepository
    ).findByTask(TaskEntityTestData.getMarketUserNewlyRegistered());

    doReturn(Optional.of(MilestoneEntityTestData.getOtherMilestone())).when(
        milestoneEntityRepository
    ).findByTask(TaskEntityTestData.getAssignedTask());

    doReturn(Optional.of(MilestoneEntityTestData.get()))
        .when(
            milestoneEntityRepository
        ).findByTask(TaskEntityTestData.get());

  }

  public static void tasksMoreThanMinimumForGrab(
      final MilestoneEntityRepository milestoneEntityRepository
  ) {
    doReturn(
        List.of(MilestoneEntityTestData.getTasksMoreThanMinimumForGrab())
    )
        .when(milestoneEntityRepository).findAllById(ProjectDTOTestData.getMinimumForGab().getMilestone());
  }

  public static void tasksEqualToMinimumForGrab(
      final MilestoneEntityRepository milestoneEntityRepository
  ) {
    doReturn(
        List.of(MilestoneEntityTestData.getTasksEqualToMinimumForGrab())
    )
        .when(milestoneEntityRepository).findAllById(ProjectDTOTestData.getMinimumForGab().getMilestone());

  }

  public static void
      emptyMilestone(final MilestoneEntityRepository milestoneEntityRepository) {
    doReturn(
        List.of(MilestoneEntityTestData.getNoTasksMilestone())
    )
        .when(milestoneEntityRepository).findAllById(ProjectDTOTestData.getMinimumForGab().getMilestone());

  }

  public static void
      multipleMilestone(
          final MilestoneEntityRepository milestoneEntityRepository
      ) {
    doReturn(
        List.of(
            MilestoneEntityTestData.getTasksEqualToMinimumForGrab(),
            MilestoneEntityTestData.getMilestoneTwo()
        )
    )
        .when(milestoneEntityRepository).findAllById(ProjectDTOTestData.getMultipleMilestonesProject().getMilestone());

  }

}
