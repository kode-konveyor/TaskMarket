package com.kodekonveyor.market.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodekonveyor.authentication.RoleEntity;
import com.kodekonveyor.authentication.RoleEntityRepository;
import com.kodekonveyor.authentication.UserEntity;
import com.kodekonveyor.authentication.UserEntityRepository;
import com.kodekonveyor.market.MarketConstants;
import com.kodekonveyor.market.project.MilestoneEntity;
import com.kodekonveyor.market.project.MilestoneEntityRepository;
import com.kodekonveyor.market.project.ProjectDTO;
import com.kodekonveyor.market.register.MarketUserEntity;
import com.kodekonveyor.market.register.MarketUserEntityRepository;
import com.kodekonveyor.market.technical.MessageUserOnDiscordService;

@Service
public class CheckUpforgrabTasksService {

  @Autowired
  private MilestoneEntityRepository milestoneEntityRepository;

  @Autowired
  private MessageUserOnDiscordService messageUserOnDiscordService;

  @Autowired
  private MarketUserEntityRepository marketUserEntityRepository;

  @Autowired
  private UserEntityRepository userEntityRepository;

  @Autowired
  private RoleEntityRepository roleEntityRepository;

  public void call(final ProjectDTO projectDTO) {

    final RoleEntity roleEntity =
        roleEntityRepository.findByName(
            projectDTO.getName() + MarketConstants.FRONT_SLASH +
                MarketConstants.PROJECT_MANAGER_ROLE
        ).get();

    final List<UserEntity> users =
        userEntityRepository.findByRole(roleEntity);

    final List<MarketUserEntity> marketUsers = new ArrayList<>();

    for (final UserEntity user : users) {
      final MarketUserEntity marketUser =
          marketUserEntityRepository.findByUser(user).get();
      marketUsers.add(marketUser);
    }

    if (countTheTasks(projectDTO) <= projectDTO.getMinimumForGrab())
      for (final MarketUserEntity marketUser : marketUsers)
        messageUserOnDiscordService.call(
            MarketConstants.UP_FOR_GRAB_TASKS_BELOW_MINIMUM_FOR_GRAB, marketUser
        );

  }

  private long countTheTasks(final ProjectDTO projectDTO) {

    final Iterable<MilestoneEntity> milestones =
        milestoneEntityRepository.findAllById(projectDTO.getMilestone());

    return StreamSupport.stream(milestones.spliterator(), false)
        .flatMap(milestone -> milestone.getTask().stream())
        .filter(task -> TaskStatusEnum.UP_FOR_GRAB.equals(task.getStatus()))
        .count();
  }

}
