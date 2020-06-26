package com.kodekonveyor.technical;

import com.kodekonveyor.annotations.TestedBehaviour;
import com.kodekonveyor.annotations.TestedService;
import com.kodekonveyor.exception.ThrowableTester;
import com.kodekonveyor.market.tasks.TaskEntityTestData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static com.kodekonveyor.technical.TechnicalTestData.EXPECTED_MSG_WHN_PR_NOT_FOUND_FOR_ISSUE;
import static com.kodekonveyor.technical.TechnicalTestData.EXP_GITHUB_MARKER;
import static com.kodekonveyor.technical.TechnicalTestData.EXP_LOG_GET_PR_FOR_ISSUE_CALL;
import static com.kodekonveyor.technical.TechnicalTestData.EXP_LOG_GET_PR_FOR_ISSUE_FAILURE;
import static com.kodekonveyor.technical.TechnicalTestData.EXP_LOG_GET_PR_FOR_ISSUE_SUCCESS;
import static com.kodekonveyor.technical.TechnicalTestData.TEST_ISSUE_ID;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("Logging")
@TestedService("GetPullRequestForIssueService")
public class GetPullRequestForIssueServiceLoggingTest extends GetPullRequestForIssueServiceTestBase {

    @Test
    @DisplayName("Start of the service is logged.")
    public void test1() {
        GetPullRequestForIssueServiceStubs.mockSuccessResponse(githubGraphqlService);
        getPullRequestForIssueService.call(TaskEntityTestData.getPullRequestIssuedTask());

        Mockito.verify(loggerService)
                .info(EXP_GITHUB_MARKER, EXP_LOG_GET_PR_FOR_ISSUE_CALL);
    }

    @Test
    @DisplayName("Successful fetching of linked pull request for issue is logged.")
    public void test2() {
        GetPullRequestForIssueServiceStubs.mockSuccessResponse(githubGraphqlService);
        getPullRequestForIssueService.call(TaskEntityTestData.getPullRequestIssuedTask());

        Mockito.verify(loggerService)
                .debug(EXP_GITHUB_MARKER, EXP_LOG_GET_PR_FOR_ISSUE_SUCCESS, TEST_ISSUE_ID);
    }

    @Test
    @DisplayName("Error is logged when PR is not found for an issue.")
    public void test3() {
        GetPullRequestForIssueServiceStubs.mockWhenNotLinked(githubGraphqlService);
        ThrowableTester.assertThrows(
                () -> getPullRequestForIssueService.call(TaskEntityTestData.getPullRequestIssuedTask())
        );

        Mockito.verify(loggerService)
                .warn(
                        EXP_GITHUB_MARKER, EXP_LOG_GET_PR_FOR_ISSUE_FAILURE,
                        TEST_ISSUE_ID, EXPECTED_MSG_WHN_PR_NOT_FOUND_FOR_ISSUE
                );
    }

    @Test
    @DisplayName("Error is logged when issue is not found or any error occurs fetching from github")
    public void test4() {
        GetPullRequestForIssueServiceStubs.mockWhenIssueNotFound(githubGraphqlService);
        ThrowableTester.assertThrows(
                () -> getPullRequestForIssueService.call(TaskEntityTestData.getPullRequestIssuedTask())
        );

        Mockito.verify(loggerService)
                .warn(
                        EXP_GITHUB_MARKER, EXP_LOG_GET_PR_FOR_ISSUE_FAILURE,
                        TEST_ISSUE_ID, EXPECTED_MSG_WHN_PR_NOT_FOUND_FOR_ISSUE
                );
    }
}
