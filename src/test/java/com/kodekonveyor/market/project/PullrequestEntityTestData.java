package com.kodekonveyor.market.project;

import javax.annotation.Generated;

import com.kodekonveyor.market.tasks.TaskEntityTestData;

@Generated("by zenta-tools")
public class PullrequestEntityTestData {

  public final static PullRequestEntity get() {
    final PullRequestEntity pullrequestEntity = new PullRequestEntity();
    pullrequestEntity.setId(PullRequestTestData.ID);
    pullrequestEntity.setTask(TaskEntityTestData.get());
    pullrequestEntity.setIsAccepted(PullRequestTestData.IS_ACCEPTED);
    pullrequestEntity.setReference(PullRequestTestData.REFERENCE);

    return pullrequestEntity;
  }

  public static PullRequestEntity getGrabbedOverThreeDays() {
    final PullRequestEntity pullrequestEntity = get();
    pullrequestEntity.setTask(TaskEntityTestData.getGrabbedOverThreeDays());
    return pullrequestEntity;
  }

  public static PullRequestEntity getGrabbedExactlyThreeDays() {
    final PullRequestEntity pullrequestEntity = get();
    pullrequestEntity.setTask(TaskEntityTestData.getGrabbedExactlyThreeDays());
    return pullrequestEntity;
  }

  public static PullRequestEntity getGrabbedForFourDays() {
    final PullRequestEntity pullrequestEntity = get();
    pullrequestEntity.setTask(TaskEntityTestData.getGrabbedForFourDays());
    return pullrequestEntity;
  }

  public static PullRequestEntity getPullRequestTask() {
    final PullRequestEntity pullrequestEntity = get();
    pullrequestEntity.setTask(TaskEntityTestData.getPullRequestIssuedTask());
    return pullrequestEntity;
  }

}
