package com.kodekonveyor.market.project;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodekonveyor.authentication.AuthenticatedUserService;
import com.kodekonveyor.authentication.RoleEntity;
import com.kodekonveyor.authentication.UserEntity;
import com.kodekonveyor.market.MarketConstants;
import com.kodekonveyor.market.UrlMapConstants;
import com.kodekonveyor.market.ValidationException;
import com.kodekonveyor.market.lead.CheckRoleUtil;
import com.kodekonveyor.market.register.MarketUserEntity;
import com.kodekonveyor.market.register.MarketUserEntityRepository;

@RestController
public class AddFundsToProjectController {

  @Autowired
  AuthenticatedUserService authenticatedUserService;

  @Autowired
  ProjectEntityRepository projectEntityRepository;

  @Autowired
  MarketUserEntityRepository marketUserEntityRepository;

  @PutMapping(UrlMapConstants.PROJECT_BUDGET_PATH)
  public ProjectDTO call(final long projectId, final long budgetInCents) {
    final ProjectEntity project =
        projectEntityRepository
            .findById(projectId).get();
    final UserEntity user = authenticatedUserService.call();
    final ProjectDTO projectDTO = getProjectDTO(project);
    validateUserBalance(user, project, budgetInCents);
    addFundsToProject(user, project, budgetInCents, projectDTO);
    return projectDTO;
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

  private void
      validateUserBalance(
          final UserEntity user, final ProjectEntity project,
          final long budgetInCents
      ) {
    final Optional<MarketUserEntity> marketUserEntity =
        marketUserEntityRepository.findByUser(user);
    final long userBalance = marketUserEntity.get().getBalanceInCents();
    if (
      userBalance <= 0 &&
          !CheckRoleUtil.hasRole(user, project, MarketConstants.MANAGER)
    )
      throw new ValidationException(
          ProjectConstants.BALANCE_IS_NEGATIVE + ProjectConstants.COMMA +
              ProjectConstants.USER_NOT_MANAGER
      );
    if (
      userBalance < budgetInCents &&
          !CheckRoleUtil.hasRole(user, project, MarketConstants.MANAGER)
    )
      throw new ValidationException(
          ProjectConstants.USER_BALANCE_IS_LESS_THAN_THE_BUDGET
      );

  }

  private void addFundsToProject(
      final UserEntity user,
      final ProjectEntity project, final long budgetInCents,
      final ProjectDTO projectDTO
  ) {
    final Optional<MarketUserEntity> marketUserEntity =
        marketUserEntityRepository.findByUser(user);
    final long userBalance = marketUserEntity.get().getBalanceInCents();
    long updatedUserBalance = userBalance - budgetInCents;
    long updatedProjectBudget;
    if (updatedUserBalance >= 0) {
      updatedProjectBudget =
          project.getBudgetInCents() + budgetInCents;
      if (updatedProjectBudget < 0)
        throw new ValidationException(
            ProjectConstants.INVALID_PROJECT_BUDGET_AMOUNT
        );

    } else {
      updatedProjectBudget =
          project.getBudgetInCents() - Math.abs(budgetInCents);
      updatedUserBalance = userBalance + Math.abs(budgetInCents);
    }
    marketUserEntity.get()
        .setBalanceInCents(updatedUserBalance);
    marketUserEntityRepository.save(marketUserEntity.get());
    project.setBudgetInCents(updatedProjectBudget);
    projectDTO.setBudgetInCents(updatedProjectBudget);
    projectEntityRepository.save(project);
  }
}
