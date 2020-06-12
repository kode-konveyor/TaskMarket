package com.kodekonveyor.market.project;

import static org.mockito.Mockito.doReturn;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class MilestoneEntityRepositoryStubs {

  public static void
      behaviour(final MilestoneEntityRepository milestoneEntityRepository) {
    doReturn(Optional.of(MilestoneEntityTestData.get()))
        .when(milestoneEntityRepository).findById(MilestoneTestData.ID);
    doReturn(Set.of(MilestoneEntityTestData.get())).when(
        milestoneEntityRepository
    ).findAllById(Set.of(MilestoneTestData.ID));
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
      noUpForGrabTasks(final MilestoneEntityRepository milestoneEntityRepository) {
    doReturn(
        List.of(MilestoneEntityTestData.getNoUpForGrabTasks())
    )
        .when(milestoneEntityRepository).findAllById(ProjectDTOTestData.getMinimumForGab().getMilestone());

  }

  public static void
      noTasks(final MilestoneEntityRepository milestoneEntityRepository) {
    doReturn(List.of())
        .when(milestoneEntityRepository).findAllById(ProjectDTOTestData.getMinimumForGab().getMilestone());

  }

  public static void
      noTasksInMileStone(
          final MilestoneEntityRepository milestoneEntityRepository
      ) {
    doReturn(
        List.of(MilestoneEntityTestData.getNobTasksInMilestone())
    )
        .when(milestoneEntityRepository).findAllById(ProjectDTOTestData.getMinimumForGab().getMilestone());

  }

}
