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
    projectDTO.setPullRequest(Set.of(PullrequestTestData.ID));
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

}
