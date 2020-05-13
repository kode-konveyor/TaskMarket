package com.kodekonveyor.market.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodekonveyor.authentication.AuthenticatedUserService;
import com.kodekonveyor.authentication.UserEntity;
import com.kodekonveyor.market.UrlMapConstants;
import com.kodekonveyor.market.project.MilestoneEntity;
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
  TaskEntityRepository taskRepository;

  @Autowired
  ProjectEntityRepository ProjectEntityRepository;

  @GetMapping(UrlMapConstants.LIST_TASK_PATH)
  public List<TaskDTO> call() {
    final UserEntity user = authenticatedUserService.call();
    final MarketUserEntity marketUserEntity =
        marketUserEntityRepository.findByUser(user).get();
    return List
        .of(
            getInProgressOrClosedTask(
                marketUserEntity, TaskStatusEnum.IN_PROGRESS
            ),
            getClosedUpForGrabTask(marketUserEntity),
            getOpenUpForGrabTask(),
            getInProgressOrClosedTask(marketUserEntity, TaskStatusEnum.DONE)
        ).stream()
        .flatMap(List::stream).map(this::convertTaskEntityToDTO).collect(Collectors.toList());
  }

  private TaskDTO
      convertTaskEntityToDTO(final TaskEntity taskEntity) {
    final TaskDTO taskDTO = createTaskDTO();
    taskDTO.setGithubId(taskEntity.getGithubId());
    return taskDTO;
  }

  private TaskDTO createTaskDTO() {
    return new TaskDTO();
  }

  private List<TaskEntity> getClosedUpForGrabTask(
      final MarketUserEntity marketUserEntity
  ) {
    final List<TaskEntity> taskEntities =
        getTaskByProjectIsPublic(false);
    return taskEntities.stream()
        .filter(
            taskEntity -> taskEntity.getMarketUser().getId()
                .equals(marketUserEntity.getId())
        ).collect(Collectors.toList());
  }

  private List<TaskEntity> getInProgressOrClosedTask(
      final MarketUserEntity marketUserEntity, final TaskStatusEnum status
  ) {
    return taskRepository.findByStatusAndMarketUser(
        status, marketUserEntity
    );
  }

  private List<TaskEntity> getOpenUpForGrabTask() {
    return getTaskByProjectIsPublic(true);
  }

  private List<TaskEntity> getTaskByProjectIsPublic(
      final boolean isPublic
  ) {
    final Set<MilestoneEntity> milestoneEntities =
        ProjectEntityRepository.findByIsPublic(isPublic).get().getMilestone();
    final List<TaskEntity> taskEntities = new ArrayList<>();
    milestoneEntities.stream().map(
        milestone -> milestone.getTask()
    ).forEach(taskEntities::addAll);
    return taskEntities;
  }

}
