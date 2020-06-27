package com.kodekonveyor.market.project;

import static com.kodekonveyor.market.MarketConstants.MANAGER;
import static com.kodekonveyor.market.MarketConstants.UNAUTHORIZED_PROJECT_MODIFICATION;

import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Sets;
import com.kodekonveyor.authentication.AuthenticatedUserService;
import com.kodekonveyor.authentication.RoleEntity;
import com.kodekonveyor.authentication.UserEntity;
import com.kodekonveyor.logging.LoggingMarkerConstants;
import com.kodekonveyor.market.UnauthorizedException;
import com.kodekonveyor.market.UrlMapConstants;
import com.kodekonveyor.market.lead.CheckRoleUtil;

@RestController
public class UpdateProjectModelController {

  @Autowired
  ProjectEntityRepository projectEntityRepository;
  @Autowired
  MilestoneEntityRepository milestoneEntityRepository;
  @Autowired
  AuthenticatedUserService authenticatedUserService;

  @Autowired
  Logger logger;

  @PutMapping(UrlMapConstants.UPDATE_PROJECT_MODEL_PATH)
  public ProjectDTO
      call(final ProjectModelDTO projectModelDTO, final String projectName) {
    logger.info(LoggingMarkerConstants.PROJECT, projectName);

    final ProjectEntity project = projectEntityRepository
        .findByName(projectName).get();

    validateAuthoization(project);

    final Set<Long> milestoneIds = projectModelDTO.getMilestone();
    project.setMilestone(
        Sets.newHashSet(
            milestoneEntityRepository
                .findAllById(milestoneIds)
        )
    );
    projectEntityRepository.save(project);
    logger.debug(
        LoggingMarkerConstants.PROJECT,
        ProjectConstants.PROJECT_DTO_RETURNED_SUCCESSFULLY + project.getId()
    );
    return getProjectDTO(project);

  }

  private void validateAuthoization(final ProjectEntity projectEntity) {
    final UserEntity sessionUser = authenticatedUserService.call();

    if (!CheckRoleUtil.hasRole(sessionUser, projectEntity, MANAGER)) {
      logger.warn(
          LoggingMarkerConstants.PROJECT,
          ProjectConstants.USER_NOT_MANAGER + sessionUser.getId()
      );
      throw new UnauthorizedException(UNAUTHORIZED_PROJECT_MODIFICATION);
    }

  }

  private ProjectDTO getProjectDTO(final ProjectEntity project) {
    final ProjectDTO projectDTO = new ProjectDTO();
    projectDTO.setBudgetInCents(project.getBudgetInCents());
    projectDTO.setDescription(project.getDescription());
    projectDTO.setId(project.getId());
    projectDTO.setIsPublic(project.getIsPublic());
    projectDTO.setUrl(project.getUrl());
    projectDTO.setName(project.getName());
    projectDTO.setProjectId(project.getProjectId());
    projectDTO
        .setMilestone(project.getMilestone().stream().map(MilestoneEntity::getId).collect(Collectors.toSet()));
    projectDTO
        .setPullRequest(
            project.getPullRequest().stream().map(PullRequestEntity::getId).collect(Collectors.toSet())
        );
    projectDTO.setRole(
        project.getRole().stream().map(RoleEntity::getId).collect(Collectors.toSet())
    );
    return projectDTO;
  }
}
