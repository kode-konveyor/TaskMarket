package com.kodekonveyor.market;

import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;

public class EntityToDTOConverterServiceStubs {

    public static void behaviour(final PullRequestEntityToDTOConverterService pullRequestEntityToDTOConverterService) {
        Mockito.doCallRealMethod()
                .when(pullRequestEntityToDTOConverterService)
                .call(any());
    }
}
