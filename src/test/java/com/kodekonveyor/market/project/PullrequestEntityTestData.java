package com.kodekonveyor.market.project;

import javax.annotation.Generated;

import com.kodekonveyor.market.tasks.TaskEntityTestData;

@Generated("by zenta-tools")
public class PullrequestEntityTestData {

  public final static PullrequestEntity get() {
    final PullrequestEntity pullrequestEntity = new PullrequestEntity();
    pullrequestEntity.setId(PullrequestTestData.ID);
    pullrequestEntity.setTask(TaskEntityTestData.get());
    pullrequestEntity.setIsAccepted(PullrequestTestData.IS_ACCEPTED);
    pullrequestEntity.setReference(PullrequestTestData.REFERENCE);

    return pullrequestEntity;
  };

}
