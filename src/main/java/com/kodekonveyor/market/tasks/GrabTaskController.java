package com.kodekonveyor.market.tasks;

import static com.kodekonveyor.market.tasks.TaskConstants.USER_NOT_ELIGIBLE_TO_GRAB;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodekonveyor.authentication.AuthenticatedUserService;
import com.kodekonveyor.authentication.RoleEntity;
import com.kodekonveyor.authentication.UserEntity;
import com.kodekonveyor.market.UrlMapConstants;
import com.kodekonveyor.market.ValidationException;
import com.kodekonveyor.market.kpi.EventEntity;
import com.kodekonveyor.market.kpi.EventEntityRepository;
import com.kodekonveyor.market.kpi.EventTypeEnum;
import com.kodekonveyor.market.project.MilestoneEntity;
import com.kodekonveyor.market.project.MilestoneEntityRepository;
import com.kodekonveyor.market.project.ProjectDTO;
import com.kodekonveyor.market.project.ProjectEntity;
import com.kodekonveyor.market.project.ProjectEntityRepository;
import com.kodekonveyor.market.project.PullRequestEntity;
import com.kodekonveyor.market.project.PullrequestEntityRepository;
import com.kodekonveyor.market.register.MarketUserEntity;
import com.kodekonveyor.market.register.MarketUserEntityRepository;

@RestController
public class GrabTaskController {

  @Autowired
  TaskEntityRepository taskEntityRepository;

  @Autowired
  AuthenticatedUserService authenticatedUserService;

  @Autowired
  MarketUserEntityRepository marketUserEntityRepository;

  @Autowired
  UpdateGithubIssueService updateGithubIssueService;

  @Autowired
  MilestoneEntityRepository milestoneEntityRepository;

  @Autowired
  ProjectEntityRepository projectEntityRepository;

  @Autowired
  CheckUpforgrabTasksService checkUpforgrabTasksService;

  @Autowired
  EventEntityRepository eventEntityRepository;

  @Autowired
  TimeInstantService timeInstantService;

  @Autowired
  PullrequestEntityRepository pullrequestEntityRepository;

  @PutMapping(UrlMapConstants.GRAB_TASK_PATH)
  public void call(final long taskId) {
    final TaskEntity taskEntity =
        taskEntityRepository.findById(taskId).get();

    final UserEntity userEntity = authenticatedUserService.call();
    final MarketUserEntity marketUserEntity =
        marketUserEntityRepository.findByUser(userEntity).get();

    validateEligibilty(marketUserEntity);
    if (!taskEntity.getStatus().equals(TaskStatusEnum.UP_FOR_GRAB))
      throw new ValidationException(TaskConstants.TASK_NOT_UP_FOR_GRAB);

    updateTask(taskEntity, marketUserEntity);
    raiseEvent(userEntity);
    taskEntityRepository.save(taskEntity);
  }

  private void validateEligibilty(final MarketUserEntity marketUserEntity) {
    final List<TaskEntity> taskForMarketUser =
        taskEntityRepository.findByMarketUser(marketUserEntity);
    if (!CollectionUtils.isEmpty(taskForMarketUser)) {
      final boolean anyTaskWithoutPR = taskForMarketUser.stream()
          .map(pullrequestEntityRepository::findByTask)
          .anyMatch(CollectionUtils::isEmpty);
      if (anyTaskWithoutPR)
        throw new ValidationException(USER_NOT_ELIGIBLE_TO_GRAB);
    }
  }

  private void updateTask(
      final TaskEntity taskEntity, final MarketUserEntity marketUserEntity
  ) {
    taskEntity.setStatus(TaskStatusEnum.IN_PROGRESS);
    taskEntity.setMarketUser(marketUserEntity);
    updateGithubIssueService.call(taskEntity);
    callUpForGrabService(taskEntity);
    taskEntity.setGrabDate(timeInstantService.call());
  }

  private void callUpForGrabService(final TaskEntity taskEntity) {
    final MilestoneEntity milestoneEntity =
        milestoneEntityRepository.findByTask(taskEntity).get();
    final ProjectEntity projectEntity =
        projectEntityRepository.findByMilestone(milestoneEntity).get();
    final ProjectDTO projectDTO = convertToDTO(projectEntity);
    checkUpforgrabTasksService.call(projectDTO);
  }

  private ProjectDTO convertToDTO(final ProjectEntity projectEntity) {
    final ProjectDTO dto = new ProjectDTO();
    dto.setId(projectEntity.getId());
    dto.setBudgetInCents(projectEntity.getBudgetInCents());
    dto.setDescription(projectEntity.getDescription());
    dto.setIsPublic(projectEntity.getIsPublic());
    dto.setProjectId(projectEntity.getProjectId());
    dto.setUrl(projectEntity.getUrl());
    dto.setName(projectEntity.getName());
    copyMilestones(projectEntity, dto);
    copyRoles(projectEntity, dto);
    copyPullRequests(projectEntity, dto);
    return dto;
  }

  private void copyPullRequests(
      final ProjectEntity projectEntity, final ProjectDTO dto
  ) {
    final Set<Long> pullRequestIds = new HashSet<>();
    if (projectEntity.getPullRequest() != null) {
      for (final PullRequestEntity entity : projectEntity.getPullRequest())
        pullRequestIds.add(entity.getId());
      dto.setPullRequest(pullRequestIds);
    }
  }

  private void copyRoles(
      final ProjectEntity projectEntity, final ProjectDTO projectDTO
  ) {
    final Set<Long> roleIds = new HashSet<>();
    for (final RoleEntity role : projectEntity.getRole())
      roleIds.add(role.getId());

    projectDTO.setRole(roleIds);
  }

  private void copyMilestones(
      final ProjectEntity projectEntity, final ProjectDTO projectDTO
  ) {
    final Set<Long> milestoneIds = new HashSet<>();
    for (final MilestoneEntity milestone : projectEntity.getMilestone())
      milestoneIds.add(milestone.getId());

    projectDTO.setMilestone(milestoneIds);
  }

  private void
      raiseEvent(final UserEntity userEntity) {
    final EventEntity event = new EventEntity();
    event.setEventType(EventTypeEnum.GRAB);
    event.setInstant(timeInstantService.call());
    event.setUser(userEntity);
    eventEntityRepository.save(event);
  }
}
