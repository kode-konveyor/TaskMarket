package com.kodekonveyor.market.project;

import java.util.Set;

import javax.annotation.Generated;

import com.kodekonveyor.authentication.RoleTestData;

@Generated("by zenta-tools")
public class ProjectDTOTestData {

  public final static ProjectDTO get() {
    final ProjectDTO projectDTO = new ProjectDTO();
    projectDTO.setId(ProjectTestData.ID);
    projectDTO.setRole(Set.of(RoleTestData.ID));
    projectDTO.setMilestone(Set.of(MilestoneTestData.ID));
    projectDTO.setPullRequest(Set.of(PullRequestTestData.ID));
    projectDTO.setName(ProjectTestData.NAME);
    projectDTO.setBudgetInCents(ProjectTestData.BUDGET_IN_CENTS);
    projectDTO.setIsPublic(ProjectTestData.IS_PUBLIC);

    return projectDTO;
  }

  public static ProjectDTO getPositiveId() {
    final ProjectDTO projectDTO = get();
    projectDTO.setId(ProjectTestData.ID_POSITIVE);
    return projectDTO;
  }

  public static ProjectDTO getNonPositiveId() {
    final ProjectDTO projectDTO = get();
    projectDTO.setId(0L);
    return projectDTO;
  }

  public static ProjectDTO getZeroId() {
    final ProjectDTO projectDTO = get();
    projectDTO.setId(ProjectTestData.ID_ZERO);
    return projectDTO;
  }

  public static ProjectDTO getInvalidName() {
    final ProjectDTO projectDTO = get();
    projectDTO.setName(ProjectTestData.INVALID_NAME);
    return projectDTO;
  }

  public static ProjectDTO getNullName() {
    final ProjectDTO projectDTO = get();
    projectDTO.setName(null);
    return projectDTO;
  }

  public static ProjectDTO getUrl() {
    final ProjectDTO projectDTO = get();
    projectDTO.setUrl(ProjectTestData.URL);
    projectDTO.setDescription(ProjectTestData.DESCRIPTION);
    projectDTO.setProjectId(ProjectTestData.PROJECT_ID);
    return projectDTO;
  };

  public static ProjectDTO getAddFunds() {
    final ProjectDTO projectDTO = getUrl();
    projectDTO.setId(ProjectTestData.ID_ADD_FUNDS);
    return projectDTO;
  };

  public static ProjectDTO getBudgetEqualToUserBalance() {
    final ProjectDTO projectDTO = get();
    projectDTO.setBudgetInCents(ProjectTestData.BUDGET_EQUAL_TO_USER_BALANCE);
    return projectDTO;
  }

  public static ProjectDTO getBudgetLessThanUserBalance() {
    final ProjectDTO projectDTO = get();
    projectDTO.setBudgetInCents(ProjectTestData.BUDGET_LESS_THAN_USER_BALANCE);
    return projectDTO;
  }

  public static ProjectDTO getUpdatedBudget() {
    final ProjectDTO projectDTO = get();
    projectDTO.setBudgetInCents(ProjectTestData.UPDATED_BUDGET);
    return projectDTO;

  }
  public static ProjectDTO getUrlAndPullRequest() {
    final ProjectDTO dto = get();
    dto.setUrl(ProjectTestData.URL);
    dto.setDescription(ProjectTestData.DESCRIPTION);
    dto.setProjectId(ProjectTestData.PROJECT_ID);
    dto.setPullRequest(Set.of(PullRequestDTOTestData.get().getId()));
    return dto;
  }
}
