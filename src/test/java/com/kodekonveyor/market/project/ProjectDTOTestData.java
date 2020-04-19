package com.kodekonveyor.market.project;

public class ProjectDTOTestData {

  public static final ProjectDTO get() {
    final ProjectDTO dto = new ProjectDTO();
    dto.setId(ProjectEntityTestData.ID);
    dto.setName(ProjectEntityTestData.NAME);
    return dto;
  }

  public static ProjectDTO getInvalidName() {
    final ProjectDTO dto = get();
    dto.setName(ProjectEntityTestData.INVALID_PROJECT_NAME);
    return dto;
  }

  public static final ProjectDTO getNonPositiveId() {
    final ProjectDTO dto = get();
    dto.setId(ProjectEntityTestData.NON_POSITIVE_ID);
    return dto;
  }

  public static final ProjectDTO getNullName() {
    final ProjectDTO dto = get();
    dto.setName(null);
    return dto;
  }

  public static ProjectDTO getPositiveId() {
    final ProjectDTO dto = get();
    dto.setId(ProjectEntityTestData.ONE_ID);
    return dto;
  }

  public static ProjectDTO getZeroId() {
    final ProjectDTO dto = get();
    dto.setId(ProjectEntityTestData.ZERO_ID);
    return dto;
  }

}
