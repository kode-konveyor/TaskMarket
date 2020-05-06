package com.kodekonveyor.market.project;

import javax.annotation.Generated;

import com.kodekonveyor.market.tasks.TaskTestData;

@Generated("by zenta-tools")
public class PullrequestDTOTestData {

  public final static PullrequestDTO get() {
    final PullrequestDTO pullrequestDTO = new PullrequestDTO();
    pullrequestDTO.setId(PullrequestTestData.ID);
    pullrequestDTO.setTask(TaskTestData.ID);
    pullrequestDTO.setIsAccepted(PullrequestTestData.IS_ACCEPTED);
    pullrequestDTO.setReference(PullrequestTestData.REFERENCE);

    return pullrequestDTO;
  };

}
