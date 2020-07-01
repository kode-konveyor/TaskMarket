package com.kodekonveyor.market.tasks;

import com.kodekonveyor.annotations.TestedBehaviour;
import com.kodekonveyor.annotations.TestedService;
import com.kodekonveyor.exception.ThrowableTester;
import com.kodekonveyor.technical.GithubAPIExecutorServiceStubs;
import com.kodekonveyor.technical.TechnicalTestData;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

import static com.kodekonveyor.technical.TechnicalTestData.GITHUB_UPDATE_ISSUE_FAILURE_RES;
import static java.util.Collections.emptySet;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("update tasks in github")
@TestedService("UpdateGithubIssueService")
public class UpdateGithubIssueServiceUpdateTasksInGithubTest
        extends UpdateGithubIssueServiceTestBase {

    @Captor
    ArgumentCaptor<Map<String, Object>> argumentCaptor;

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

    @Test
    @DisplayName("When task is not assigned, the assignees is empty in request sent to update issue in github.")
    public void test4() {
        GithubAPIExecutorServiceStubs.mockUpdateIssueSuccessAndCaptureReq(githubAPIExecutorService, argumentCaptor);

        updateGithubIssueService.call(TaskEntityTestData.getStatusUngrabbed());

        Assert.assertEquals(argumentCaptor.getValue().get(TechnicalTestData.ASSIGNEES), emptySet());
    }

    @Test
    @DisplayName("When status is null for task, them labels is empty in request sent to update issue in github.")
    public void test5() {
        GithubAPIExecutorServiceStubs.mockUpdateIssueSuccessAndCaptureReq(githubAPIExecutorService, argumentCaptor);

        updateGithubIssueService.call(TaskEntityStatusTestData.getMarketUserStatusNull());

        Assert.assertEquals(argumentCaptor.getValue().get(TechnicalTestData.LABELS), emptySet());
    }

}
