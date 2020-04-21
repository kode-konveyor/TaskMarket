package com.kodekonveyor.market.project;

import java.util.List;

public class ProjectEntityTestData {

  public static final ProjectEntity get() {
    final ProjectEntity entity = new ProjectEntity();
    entity.setId(ProjectDTOTestData.ID);
    entity.setName(ProjectDTOTestData.NAME);
    entity.setMilestones(ModelExcerptDTOTestData.MILESTONE);
    return entity;
  }

  public static Object list() {
    return List.of(get());
  }

  public static ProjectEntity getNullMilestone() {
    final ProjectEntity entity = get();
    entity.setMilestones(null);
    return entity;
  }

  public static Object listNullMilestone() {
    return List.of(getNullMilestone());

  }

}
