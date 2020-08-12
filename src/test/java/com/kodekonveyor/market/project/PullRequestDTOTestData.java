package com.kodekonveyor.market.project;

import com.kodekonveyor.market.tasks.TaskTestData;

import javax.annotation.Generated;

import static com.kodekonveyor.market.project.PullRequestTestData.ID_NOT_FOUND;

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

  public final static PullRequestDTO getTaskNotFound() {
    final PullRequestDTO pullrequestDTO = get();
    pullrequestDTO.setId(ID_NOT_FOUND);
    return pullrequestDTO;
  };

}
