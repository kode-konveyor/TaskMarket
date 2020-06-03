package com.kodekonveyor.technical;

import static org.mockito.Mockito.doReturn;

import com.kodekonveyor.market.tasks.TaskEntityTestData;
import com.kodekonveyor.market.technical.GetPullRequestForIssueService;

public class GetPullRequestForIssueServiceStubs {

  public static void
      behaviour(
          final GetPullRequestForIssueService getPullRequestForIssueService
      ) {
    doReturn(TaskEntityTestData.get().getGithubId())
        .when(getPullRequestForIssueService)
        .call(TaskEntityTestData.get());
  }
}
