package com.kodekonveyor.technical;

import com.kodekonveyor.annotations.TestedBehaviour;
import com.kodekonveyor.annotations.TestedService;
import com.kodekonveyor.exception.ThrowableTester;
import com.kodekonveyor.market.ValidationException;
import com.kodekonveyor.market.tasks.TaskEntityTestData;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static com.kodekonveyor.technical.TechnicalTestData.EXPECTED_MSG_WHN_MORE_THAN_ONE_PR_FOR_ISSUE;
import static com.kodekonveyor.technical.TechnicalTestData.EXPECTED_MSG_WHN_PR_NOT_FOUND_FOR_ISSUE;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("Get Pull Request")
@TestedService("GetPullRequestForIssueService")
public class GetPullRequestForIssueServiceGetPullRequestTest extends GetPullRequestForIssueServiceTestBase {

    @Test
    @DisplayName("Pull request number is successfully returned, when linked pr is found for an issue.")
    public void test1() {
        GetPullRequestForIssueServiceStubs.mockSuccessResponse(githubGraphqlService);
        Long prReturned = getPullRequestForIssueService.call(TaskEntityTestData.getPullRequestIssuedTask());

        Assert.assertEquals(prReturned, TechnicalTestData.TEST_PR_ID);
    }

    @Test
    @DisplayName("Validation Error is thrown, when no linked pull request is found for the issue.")
    public void test2() {
        GetPullRequestForIssueServiceStubs.mockWhenNotLinked(githubGraphqlService);
        ThrowableTester.assertThrows(() -> getPullRequestForIssueService.call(TaskEntityTestData.getPullRequestIssuedTask()))
                .assertException(ValidationException.class);

    }

    @Test
    @DisplayName("When no linked pull request is found for the issue, error message is : Pull request not found for issue.")
    public void test3() {
        GetPullRequestForIssueServiceStubs.mockWhenNotLinked(githubGraphqlService);
        ThrowableTester.assertThrows(() -> getPullRequestForIssueService.call(TaskEntityTestData.getPullRequestIssuedTask()))
                .assertMessageIs(EXPECTED_MSG_WHN_PR_NOT_FOUND_FOR_ISSUE);

    }

    @Test
    @DisplayName("Validation Error is thrown, when issue is not found in github")
    public void test4() {
        GetPullRequestForIssueServiceStubs.mockWhenIssueNotFound(githubGraphqlService);
        ThrowableTester.assertThrows(() -> getPullRequestForIssueService.call(TaskEntityTestData.getPullRequestIssuedTask()))
                .assertException(ValidationException.class);

    }

    @Test
    @DisplayName("When issue is not found in github, error message is : Pull request not found for issue.")
    public void test5() {
        GetPullRequestForIssueServiceStubs.mockWhenIssueNotFound(githubGraphqlService);
        ThrowableTester.assertThrows(() -> getPullRequestForIssueService.call(TaskEntityTestData.getPullRequestIssuedTask()))
                .assertMessageIs(EXPECTED_MSG_WHN_PR_NOT_FOUND_FOR_ISSUE);

    }

    @Test
    @DisplayName("Validation Error is thrown, when more than one pull request is found in github")
    public void test6() {
        GetPullRequestForIssueServiceStubs.mockWhenMultipleIssuesFound(githubGraphqlService);
        ThrowableTester.assertThrows(() -> getPullRequestForIssueService.call(TaskEntityTestData.getPullRequestIssuedTask()))
                .assertException(ValidationException.class);

    }

    @Test
    @DisplayName("When more than one pull request is found, error message is : More than one pull request found.")
    public void test7() {
        GetPullRequestForIssueServiceStubs.mockWhenMultipleIssuesFound(githubGraphqlService);
        ThrowableTester.assertThrows(() -> getPullRequestForIssueService.call(TaskEntityTestData.getPullRequestIssuedTask()))
                .assertMessageIs(EXPECTED_MSG_WHN_MORE_THAN_ONE_PR_FOR_ISSUE);

    }
}
