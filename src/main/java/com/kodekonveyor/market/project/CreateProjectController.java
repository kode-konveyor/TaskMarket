package com.kodekonveyor.market.project;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kodekonveyor.authentication.AuthenticatedUserService;
import com.kodekonveyor.authentication.RoleEntity;
import com.kodekonveyor.authentication.RoleEntityRepository;
import com.kodekonveyor.authentication.UserEntity;
import com.kodekonveyor.logging.LoggingMarkerConstants;
import com.kodekonveyor.market.MarketConstants;
import com.kodekonveyor.market.UnauthorizedException;
import com.kodekonveyor.market.UrlMapConstants;
import com.kodekonveyor.market.ValidationException;
import com.kodekonveyor.market.lead.CheckRoleUtil;

@RestController
public class CreateProjectController {

  @Autowired
  AuthenticatedUserService authenticatedUserService;

  @Autowired
  Logger loggerService;

  @Autowired
  ProjectEntityRepository projectEntityRepository;

  @Autowired
  RoleEntityRepository roleEntityRepository;

  @Autowired
  MilestoneEntityRepository milestoneEntityRepository;

  @Autowired
  PullrequestEntityRepository pullrequestEntityRepository;

  @PostMapping(
      value = UrlMapConstants.PROJECT_PATH, consumes = "application/json"
  )
  public ProjectDTO call(@RequestBody final ProjectDTO dto) {
    final UserEntity user = authenticatedUserService.call();
    final ProjectEntity project =
        projectEntityRepository
            .findByName(MarketConstants.KODE_KONVEYOR_PROJECT_NAME).get();
    if (
      !CheckRoleUtil.hasRole(user, project, MarketConstants.MANAGER)

    )
      throw new UnauthorizedException(ProjectConstants.IN_CREATE_PROJECT);

    inputValidation(dto);
    storage(dto);

    return dto;
  }

  @PostMapping(
      value = UrlMapConstants.PROJECT_PATH,
      consumes = "application/x-www-form-urlencoded"
  )
  public ProjectDTO callForUrlencoded(final ProjectDTO projectDTO) {
    return call(projectDTO);

  }

  private void inputValidation(final ProjectDTO dto) {
    if (null == dto.getName())
      throw new ValidationException(
          MarketConstants.PROJECT_NAME_NULL_EXCEPTION
      );

    if (!dto.getName().matches(MarketConstants.PROJECT_NAME_REGEX))
      throw new ValidationException(
          MarketConstants.PROJECT_NAME_INVALID_EXCEPTION
      );

    if (dto.getId() < MarketConstants.MINIMUM_PROJECT_ID)
      throw new ValidationException(
          MarketConstants.PROJECT_ID_NON_POSITIVE_EXCEPTION
      );
  }

  private void storage(final ProjectDTO dto) {
    loggerService.info(
        LoggingMarkerConstants.PROJECT,
        ProjectConstants.PROJECT_RECEIVED + dto.toString()
    );
    final ProjectEntity entity = new ProjectEntity();
    entity.setName(dto.getName());
    entity.setIsPublic(dto.getIsPublic());
    entity.setBudgetInCents(0L);
    copyMileStones(dto, entity);
    copyPullrequests(dto, entity);
    copyRoles(dto, entity);

    projectEntityRepository.save(entity);

  }

  private void
      copyMileStones(final ProjectDTO dto, final ProjectEntity entity) {
    final Set<MilestoneEntity> milestones = new HashSet<>();
    for (final Long milestoneId : dto.getMilestone()) {
      final MilestoneEntity milestone = milestoneEntityRepository
          .findById(milestoneId).get();
      milestones.add(milestone);
    }
    entity.setMilestone(milestones);
  }

  private void
      copyPullrequests(final ProjectDTO dto, final ProjectEntity entity) {
    final Set<PullrequestEntity> pullrequests = new HashSet<>();
    for (final Long pullrequestId : dto.getPullRequest()) {
      final PullrequestEntity milestone = pullrequestEntityRepository
          .findById(pullrequestId).get();
      pullrequests.add(milestone);
    }
    entity.setPullRequest(pullrequests);
  }

  private void
      copyRoles(final ProjectDTO dto, final ProjectEntity entity) {
    final Set<RoleEntity> roles = new HashSet<>();
    for (final Long roleId : dto.getRole()) {
      final RoleEntity role = roleEntityRepository
          .findById(roleId).get();
      roles.add(role);
    }
    entity.setRole(roles);
  }

}
