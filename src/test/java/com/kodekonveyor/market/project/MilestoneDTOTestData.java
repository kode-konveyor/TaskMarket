package com.kodekonveyor.market.project;

import java.util.Set;

import javax.annotation.Generated;

import com.kodekonveyor.market.tasks.TaskTestData;

@Generated("by zenta-tools")
public class MilestoneDTOTestData {

  public final static MilestoneDTO get() {
    final MilestoneDTO milestoneDTO = new MilestoneDTO();
    milestoneDTO.setId(MilestoneTestData.ID);
    milestoneDTO.setTask(Set.of(TaskTestData.ID));
    milestoneDTO.setName(MilestoneTestData.NAME);
    milestoneDTO.setOrder(MilestoneTestData.ORDER);
    milestoneDTO.setIsActive(MilestoneTestData.IS_ACTIVE);

    return milestoneDTO;
  };

}
