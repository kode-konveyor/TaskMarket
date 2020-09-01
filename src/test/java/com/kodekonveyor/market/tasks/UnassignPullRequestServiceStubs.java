package com.kodekonveyor.market.tasks;

import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;

public class UnassignPullRequestServiceStubs {
    public static void behaviour(final UnassignPullRequestService unassignPullRequestService) {
        Mockito.doReturn(TaskEntityTestData.get()).when(unassignPullRequestService).call(any());
    }
}
