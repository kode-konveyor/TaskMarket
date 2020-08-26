package com.kodekonveyor.market;

import com.kodekonveyor.market.project.PullrequestEntityTestData;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;

public class DTOToEntityConverterServiceStubs {

    public static void behaviour(final PullRequestDTOToEntityConverterService pullRequestDtoToEntityConverterService) {
        Mockito.doReturn(PullrequestEntityTestData.get())
                .when(pullRequestDtoToEntityConverterService)
                .call(any());
    }
}
