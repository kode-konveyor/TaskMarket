package com.kodekonveyor.market.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodekonveyor.authentication.AuthenticatedUserService;
import com.kodekonveyor.authentication.RoleEntity;
import com.kodekonveyor.authentication.UserEntity;
import com.kodekonveyor.logging.LoggingMarkerConstants;
import com.kodekonveyor.market.UrlMapConstants;
import com.kodekonveyor.market.project.ProjectEntity;
import com.kodekonveyor.market.project.ProjectEntityRepository;
import com.kodekonveyor.market.register.MarketUserEntity;
import com.kodekonveyor.market.register.MarketUserEntityRepository;

@RestController
public class ListTasksController {

  @Autowired
  AuthenticatedUserService authenticatedUserService;

  @Autowired
  MarketUserEntityRepository marketUserEntityRepository;

  @Autowired
  TaskEntityRepository taskEntityRepository;

  @Autowired
  ProjectEntityRepository projectEntityRepository;

  @Autowired
  Logger logger;

  @GetMapping(UrlMapConstants.LIST_TASK_PATH)
  public List<TaskEntity> call() {

    final UserEntity user = authenticatedUserService.call();
    logger.info(LoggingMarkerConstants.TASK, user.getId().toString());
    final Optional<MarketUserEntity> marketUser =
        marketUserEntityRepository.findByUser(user);

    if (marketUser.isEmpty())
      return upForGrabTasks(marketUser);

    final List<TaskEntity> usersInProgressTasks = taskEntityRepository
        .findByStatusAndMarketUser(TaskStatusEnum.IN_PROGRESS, marketUser.get());

    final List<TaskEntity> usersClosedTasks = taskEntityRepository
        .findByStatusAndMarketUser(TaskStatusEnum.DONE, marketUser.get());

    final List<TaskEntity> userTasksList = new ArrayList<>();

    userTasksList.addAll(usersInProgressTasks);
    userTasksList.addAll(upForGrabTasks(marketUser));
    userTasksList.addAll(usersClosedTasks);

    logger.debug(
        LoggingMarkerConstants.TASK,
        TaskConstants.TASKS_RETURNED_SUCCESSFULLY + userTasksList.stream()
            .map(TaskEntity::getId).collect(Collectors.toList())
    );
    return userTasksList;
  }

  private List<TaskEntity>
      upForGrabTasks(final Optional<MarketUserEntity> marketUser) {

    final List<ProjectEntity> userAccessableprojects = new ArrayList<>();

    if (!marketUser.isEmpty()) {

      final List<ProjectEntity> privateProjects =
          projectEntityRepository.findByIsPublic(false);

      final Set<RoleEntity> userRoles = marketUser.get().getUser().getRole();

      for (final ProjectEntity projectEntity : privateProjects)
        for (final RoleEntity roleEntity : userRoles)
          if (projectEntity.getRole().contains(roleEntity))
            userAccessableprojects.add(projectEntity);
    }

    final List<ProjectEntity> publicProjects =
        projectEntityRepository.findByIsPublic(true);

    userAccessableprojects.addAll(publicProjects);

    return StreamSupport.stream(
        userAccessableprojects.spliterator(), true
    ).flatMap(project -> project.getMilestone().stream()).flatMap(
        milestone -> milestone.getTask().stream()
    ).filter(task -> TaskStatusEnum.UP_FOR_GRAB.equals(task.getStatus()))
        .collect(Collectors.toList());

  }

}
