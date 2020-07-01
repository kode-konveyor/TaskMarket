package com.kodekonveyor.market.tasks;

import com.kodekonveyor.annotations.TestedBehaviour;
import com.kodekonveyor.annotations.TestedService;
import com.kodekonveyor.exception.ThrowableTester;
import com.kodekonveyor.technical.GithubAPIExecutorServiceStubs;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.web.server.ResponseStatusException;

import static com.kodekonveyor.technical.TechnicalTestData.GITHUB_UPDATE_ISSUE_FAILURE_RES;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("update tasks in github")
@TestedService("UpdateGithubIssueService")
public class UpdateGithubIssueServiceUpdateTasksInGithubTest
        extends UpdateGithubIssueServiceTestBase {

    @Test
    @DisplayName("When task entity is successfully updated, no error is thrown.")
    public void test1() {
        GithubAPIExecutorServiceStubs.mockUpdateIssueSuccessBehaviour(githubAPIExecutorService);

        ThrowableTester.assertNoException(() -> updateGithubIssueService.call(TaskEntityTestData.get()));

    }

    @Test
    @DisplayName("When github api call to update issue is failed with exception ,then corresponding exception is thrown.")
    public void test2() {
        GithubAPIExecutorServiceStubs.mockUpdateIssueFailureBehaviour(githubAPIExecutorService);

        ThrowableTester.assertThrows(() -> updateGithubIssueService.call(TaskEntityTestData.get()))
                .assertException(ResponseStatusException.class);

    }

    @Test
    @DisplayName("When github api call to update issue is failed with exception ,then corresponding message is present.")
    public void test3() {
        GithubAPIExecutorServiceStubs.mockUpdateIssueFailureBehaviour(githubAPIExecutorService);

        ThrowableTester.assertThrows(() -> updateGithubIssueService.call(TaskEntityTestData.get()))
                .assertMessageContains(GITHUB_UPDATE_ISSUE_FAILURE_RES);

    }
}
