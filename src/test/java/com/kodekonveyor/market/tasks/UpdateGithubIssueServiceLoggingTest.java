package com.kodekonveyor.market.tasks;

import com.kodekonveyor.annotations.TestedBehaviour;
import com.kodekonveyor.annotations.TestedService;
import com.kodekonveyor.exception.ThrowableTester;
import com.kodekonveyor.technical.GithubAPIExecutorServiceStubs;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static com.kodekonveyor.logging.LoggingMarkerConstants.TASK;
import static com.kodekonveyor.market.tasks.TaskTestData.EXP_LOG_UPDATE_GITHUB_ISSUE_SERVICE_CALL;
import static com.kodekonveyor.market.tasks.TaskTestData.EXP_LOG_UPDATE_GITHUB_ISSUE_SERVICE_FAILURE_CALL;
import static com.kodekonveyor.market.tasks.TaskTestData.EXP_LOG_UPDATE_GITHUB_ISSUE_SERVICE_SUCCESS_CALL;
import static com.kodekonveyor.market.tasks.TaskTestData.ID;
import static com.kodekonveyor.technical.TechnicalTestData.GITHUB_UPDATE_ISSUE_FAILURE_RES;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("Logging")
@TestedService("UpdateGithubIssueService")
public class UpdateGithubIssueServiceLoggingTest
        extends UpdateGithubIssueServiceTestBase {

    @Test
    @DisplayName("Start of call to UpdateGithubIssueService is logged.")
    public void test1() {
        GithubAPIExecutorServiceStubs.mockUpdateIssueSuccessBehaviour(githubAPIExecutorService);

        updateGithubIssueService.call(TaskEntityTestData.get());

        Mockito.verify(logger)
                .info(TASK, EXP_LOG_UPDATE_GITHUB_ISSUE_SERVICE_CALL, ID);
    }

    @Test
    @DisplayName("End of call to UpdateGithubIssueService is logged.")
    public void test2() {
        GithubAPIExecutorServiceStubs.mockUpdateIssueSuccessBehaviour(githubAPIExecutorService);

        updateGithubIssueService.call(TaskEntityTestData.get());

        Mockito.verify(logger)
                .debug(TASK, EXP_LOG_UPDATE_GITHUB_ISSUE_SERVICE_SUCCESS_CALL, ID);

    }

    @Test
    @DisplayName("When github api call to update issue is failed with exception ,then corresponding message is logged.")
    public void test3() {
        GithubAPIExecutorServiceStubs.mockUpdateIssueFailureBehaviour(githubAPIExecutorService);

        ThrowableTester.assertThrows(() -> updateGithubIssueService.call(TaskEntityTestData.get()));

        Mockito.verify(logger)
                .warn(
                        TASK, EXP_LOG_UPDATE_GITHUB_ISSUE_SERVICE_FAILURE_CALL,
                        ID,
                        GITHUB_UPDATE_ISSUE_FAILURE_RES
                );

    }
}
