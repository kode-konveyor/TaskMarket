package com.kodekonveyor.market.project;

import javax.annotation.Generated;

@Generated("by zenta-tools")
public class MilestoneEntityTestData {

  public final static MilestoneEntity get() {
    final MilestoneEntity milestoneEntity = new MilestoneEntity();
    milestoneEntity.setId(MilestoneTestData.ID);
    milestoneEntity.setName(MilestoneTestData.NAME);
    milestoneEntity.setPriority(MilestoneTestData.ORDER);
    milestoneEntity.setIsActive(MilestoneTestData.IS_ACTIVE);

    return milestoneEntity;
  };

}
