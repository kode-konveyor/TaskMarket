package com.kodekonveyor.market.project;

import com.kodekonveyor.market.tasks.TaskDTOTestData;

public class ModelExcerptDTOTestData {

  public static final String MILESTONE = "first milestone";

  public static ModelExcerptDTO get() {
    final ModelExcerptDTO dto = new ModelExcerptDTO();
    dto.setMilestones(MILESTONE);
    dto.setTasks(TaskDTOTestData.get());
    return dto;
  }

}
