package com.kodekonveyor.market.project;

import java.util.List;

public class ProjectEntityTestData {

  public final static Long ID = (long) 442;
  public final static Long ID_NO_MILESTONES = (long) 442;
  public final static String INVALID_PROJECT_NAME =
      "kode-konveyor/Tas%$#@kMarket";
  public final static String NAME = "kode-konveyor/TaskMarket";
  public final static String NAME_MILESTONE_NULL =
      "kode-konveyor/TaskMarketNullMilestoned";
  public static final Long NON_POSITIVE_ID = (long) -442;
  public static final Long ONE_ID = (long) 1;
  public static final Long ZERO_ID = (long) 0;

  public static final ProjectEntity get() {
    final ProjectEntity entity = new ProjectEntity();
    entity.setId(ID);
    entity.setName(NAME);
    entity.setMilestones(null);
    return entity;
  }

  public static List<ProjectEntity> list() {
    return List.of(get());
  }

  public static ProjectEntity getMilestonesUpdated() {
    final ProjectEntity entity = get();
    entity.setMilestones(ModelExcerptDTOTestData.MILESTONE);
    return entity;
  }

  public static List<ProjectEntity> listMilestonesUpdated() {
    return List.of(getMilestonesUpdated());

  }

}
