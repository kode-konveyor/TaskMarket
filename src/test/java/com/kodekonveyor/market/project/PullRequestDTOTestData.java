package com.kodekonveyor.market.project;

import javax.annotation.Generated;

import com.kodekonveyor.market.tasks.TaskTestData;

@Generated("by zenta-tools")
public class PullRequestDTOTestData {

  public final static PullRequestDTO get() {
    final PullRequestDTO pullrequestDTO = new PullRequestDTO();
    pullrequestDTO.setId(PullRequestTestData.ID);
    pullrequestDTO.setTask(TaskTestData.ID);
    pullrequestDTO.setIsAccepted(PullRequestTestData.IS_ACCEPTED);
    pullrequestDTO.setReference(PullRequestTestData.REFERENCE);

    return pullrequestDTO;
  };

}
