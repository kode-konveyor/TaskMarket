package com.kodekonveyor.market.tasks;

import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodekonveyor.market.MarketConstants;
import com.kodekonveyor.market.project.MilestoneEntity;
import com.kodekonveyor.market.project.MilestoneEntityRepository;
import com.kodekonveyor.market.project.ProjectConstants;
import com.kodekonveyor.market.project.ProjectDTO;
import com.kodekonveyor.market.technical.MessageUserOnDiscordService;

@Service
public class CheckUpforgrabTasksService {

  @Autowired
  private MilestoneEntityRepository milestoneEntityRepository;

  @Autowired
  private MessageUserOnDiscordService messageUserOnDiscordService;

  public void call(final ProjectDTO projectDTO) {

    if (countTheTasks(projectDTO) <= projectDTO.getMinimumForGrab())
      messageUserOnDiscordService.call(
          MarketConstants.UP_FOR_GRAB_TASKS_BELOW_MINIMUM_FOR_GRAB
      );

  }

  private long countTheTasks(final ProjectDTO projectDTO) {

    if (projectDTO.getMilestone().isEmpty())
      return ProjectConstants.NO_UP_FOR_GRAB_TASKS;

    final Iterable<MilestoneEntity> milestones =
        milestoneEntityRepository.findAllById(projectDTO.getMilestone());

    return StreamSupport.stream(milestones.spliterator(), false)
        .flatMap(milestone -> milestone.getTask().stream())
        .filter(task -> TaskStatusEnum.UP_FOR_GRAB.equals(task.getStatus()))
        .count();
  }

}
