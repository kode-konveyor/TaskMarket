package com.kodekonveyor.technical;

import com.kodekonveyor.market.technical.GithubAPIExecutorService;
import org.mockito.ArgumentCaptor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

import static java.util.Collections.emptyMap;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.http.HttpMethod.PATCH;

public class GithubAPIExecutorServiceStubs {

    public static void mockUpdateIssueSuccessBehaviour(final GithubAPIExecutorService githubAPIExecutorService) {
        doReturn(emptyMap()).when(githubAPIExecutorService).call(eq(PATCH), anyString(), any(), any());
    }

    public static void mockUpdateIssueFailureBehaviour(final GithubAPIExecutorService githubAPIExecutorService) {
        doThrow(
                new ResponseStatusException(
                        HttpStatus.INTERNAL_SERVER_ERROR, TechnicalTestData.GITHUB_UPDATE_ISSUE_FAILURE_RES
                )
        ).when(githubAPIExecutorService).call(eq(PATCH), anyString(), any(), any());
    }

    public static void mockUpdateIssueSuccessAndCaptureReq(final GithubAPIExecutorService githubAPIExecutorService,
                                                           final ArgumentCaptor<Map<String, Object>> captor) {
        doReturn(emptyMap()).when(githubAPIExecutorService).call(eq(PATCH), anyString(), captor.capture(), any());
    }

}
