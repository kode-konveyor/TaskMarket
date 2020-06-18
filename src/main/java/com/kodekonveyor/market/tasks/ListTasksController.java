package com.kodekonveyor.market.tasks;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodekonveyor.authentication.AuthenticatedUserService;
import com.kodekonveyor.authentication.RoleEntity;
import com.kodekonveyor.authentication.UserEntity;
import com.kodekonveyor.market.UrlMapConstants;
import com.kodekonveyor.market.project.MilestoneEntity;
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

  @GetMapping(UrlMapConstants.LIST_TASK_PATH)
  public List<TaskEntity> call() {

    final UserEntity user = authenticatedUserService.call();
    final MarketUserEntity marketUser =
        marketUserEntityRepository.findByUser(user).get();

    final List<TaskEntity> usersInProgressTasks = taskEntityRepository
        .findByStatusAndMarketUser(TaskStatusEnum.IN_PROGRESS, marketUser);

    final List<TaskEntity> usersClosedTasks = taskEntityRepository
        .findByStatusAndMarketUser(TaskStatusEnum.DONE, marketUser);

    final List<TaskEntity> userTasksList = new ArrayList<>();

    userTasksList.addAll(usersInProgressTasks);
    userTasksList.addAll(upForGrabTasksFromPrivateProjects(marketUser));
    userTasksList.addAll(upForGrabtasksFromPublicProjects());
    userTasksList.addAll(usersClosedTasks);

    return userTasksList;

  }

  private List<TaskEntity> upForGrabtasksFromPublicProjects() {
    final List<ProjectEntity> publicProjects =
        projectEntityRepository.findByIsPublic(true);

    final Set<MilestoneEntity> publicprojectMilestones = new HashSet<>();
    final Set<TaskEntity> setOfTasksInpublicProject = new HashSet<>();

    for (final ProjectEntity projectEntity : publicProjects)
      publicprojectMilestones.addAll(projectEntity.getMilestone());

    for (final MilestoneEntity milestoneEntity : publicprojectMilestones)
      setOfTasksInpublicProject.addAll(milestoneEntity.getTask());

    final List<TaskEntity> upforGrabTasksInPublicProject = new ArrayList<>();

    for (final TaskEntity taskEntity : setOfTasksInpublicProject)
      if (taskEntity.getStatus().equals(TaskStatusEnum.UP_FOR_GRAB))
        upforGrabTasksInPublicProject.add(taskEntity);
    return upforGrabTasksInPublicProject;
  }

  private List<TaskEntity>
      upForGrabTasksFromPrivateProjects(final MarketUserEntity marketUser) {
    final List<ProjectEntity> privateProjects =
        projectEntityRepository.findByIsPublic(false);

    final Set<RoleEntity> userRoles = marketUser.getUser().getRole();

    final List<ProjectEntity> privateProjectsUserMember = new ArrayList<>();

    for (final ProjectEntity projectEntity : privateProjects)
      for (final RoleEntity roleEntity : userRoles)
        if (roleEntity.getName().contains(projectEntity.getName()))
          privateProjectsUserMember.add(projectEntity);

    final Set<MilestoneEntity> privateprojectMilestones = new HashSet<>();
    final Set<TaskEntity> setOfTasksInPrivateProject = new HashSet<>();

    for (final ProjectEntity projectEntity : privateProjectsUserMember)
      privateprojectMilestones.addAll(projectEntity.getMilestone());

    for (final MilestoneEntity milestoneEntity : privateprojectMilestones)
      setOfTasksInPrivateProject.addAll(milestoneEntity.getTask());

    final List<TaskEntity> upforGrabTasksInPrivateProject = new ArrayList<>();

    for (final TaskEntity taskEntity : setOfTasksInPrivateProject)
      if (taskEntity.getStatus().equals(TaskStatusEnum.UP_FOR_GRAB))
        upforGrabTasksInPrivateProject.add(taskEntity);

    return upforGrabTasksInPrivateProject;
  }

}
