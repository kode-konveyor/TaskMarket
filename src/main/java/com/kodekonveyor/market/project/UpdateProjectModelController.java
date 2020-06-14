package com.kodekonveyor.market.project;

import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Sets;
import com.kodekonveyor.authentication.RoleEntity;
import com.kodekonveyor.logging.LoggingMarkerConstants;
import com.kodekonveyor.market.UrlMapConstants;

@RestController
public class UpdateProjectModelController {

  @Autowired
  ProjectEntityRepository projectEntityRepository;
  @Autowired
  MilestoneEntityRepository milestoneEntityRepository;

  @Autowired
  Logger logger;

  @PutMapping(UrlMapConstants.UPDATE_PROJECT_MODEL_PATH)
  public ProjectDTO
      call(final ProjectModelDTO projectModelDTO, final String projectName) {
    logger.info(LoggingMarkerConstants.PROJECT, projectName);

    final ProjectEntity project = projectEntityRepository
        .findByName(projectName).get();
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
