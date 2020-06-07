package com.kodekonveyor.market.tasks;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.kodekonveyor.annotations.TestedBehaviour;
import com.kodekonveyor.annotations.TestedService;
import com.kodekonveyor.market.project.PullRequestEntityStubs;
import com.kodekonveyor.technical.GithubGraphqlServiceStubs;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("Check grab eligibility")
@TestedService("UngrabService")
public class UngrabServiceCheckGrabEligibility2Test
    extends UngrabServiceTestBase {

  @Test
  @DisplayName(
    "Nothing is done, if the difference between latest SUCCESSFULL commit snd review is less than 3 days"
  )
  void test() {
    PullRequestEntityStubs
        .allPullRequests(pullrequestEntityRepository);
    GithubGraphqlServiceStubs
        .successfullReviewAndCommitUnderThreeDays(githubGraphqlService);
    TaskEntityRepositoryStubs.behaviour(taskEntityRepository);
    ungrabService.call();
    Mockito.verify(taskEntityRepository, Mockito.times(0))
        .save(TaskEntityTestData.getUngrabPullRequestIssuedtask());
  }

  @Test
  @DisplayName(
    "Task is ungrabbed, if the difference between latest SUCCESSFULL commit snd review is equal to 4 days"
  )
  void test1() {
    PullRequestEntityStubs
        .allPullRequests(pullrequestEntityRepository);
    GithubGraphqlServiceStubs
        .successfullReviewAndCommitEqualToFourDays(githubGraphqlService);
    TaskEntityRepositoryStubs.behaviour(taskEntityRepository);
    ungrabService.call();
    Mockito.verify(taskEntityRepository)
        .save(TaskEntityTestData.getUngrabPullRequestIssuedtask());
  }

  @Test
  @DisplayName(
    "Task is ungrabbed, if the difference between latest SUCCESSFULL commit snd review is more than 3 days"
  )
  void test2() {
    PullRequestEntityStubs
        .allPullRequests(pullrequestEntityRepository);
    GithubGraphqlServiceStubs
        .successfullReviewAndCommitMoreThanDays(githubGraphqlService);
    TaskEntityRepositoryStubs.behaviour(taskEntityRepository);
    ungrabService.call();
    Mockito.verify(taskEntityRepository)
        .save(TaskEntityTestData.getUngrabPullRequestIssuedtask());
  }

  @Test
  @DisplayName(
    "Task is ungrabbed, if commit is in FAiLURE state for more than 3 days"
  )
  void test3() {
    PullRequestEntityStubs
        .allPullRequests(pullrequestEntityRepository);
    GithubGraphqlServiceStubs
        .failureCommitMoreThanThreeDays(githubGraphqlService);
    TaskEntityRepositoryStubs.behaviour(taskEntityRepository);
    ungrabService.call();
    Mockito.verify(taskEntityRepository)
        .save(TaskEntityTestData.getUngrabPullRequestIssuedtask());
  }

  @Test
  @DisplayName(
    "Task is ungrabbed, if commit is in FAiLURE state for 4 days"
  )
  void test4() {
    PullRequestEntityStubs
        .allPullRequests(pullrequestEntityRepository);
    GithubGraphqlServiceStubs
        .failureCommitEqualToFourDays(githubGraphqlService);
    TaskEntityRepositoryStubs.behaviour(taskEntityRepository);
    ungrabService.call();
    Mockito.verify(taskEntityRepository)
        .save(TaskEntityTestData.getUngrabPullRequestIssuedtask());
  }

  @Test
  @DisplayName(
    "Nothing is done, if commit is in FAiLURE state less than 3 days"
  )
  void test5() {
    PullRequestEntityStubs
        .allPullRequests(pullrequestEntityRepository);
    GithubGraphqlServiceStubs
        .failureCommitEqualLessThanThreeDays(githubGraphqlService);
    TaskEntityRepositoryStubs.behaviour(taskEntityRepository);
    ungrabService.call();
    Mockito.verify(taskEntityRepository, Mockito.times(0))
        .save(TaskEntityTestData.getUngrabPullRequestIssuedtask());
  }

  //  @Test
  //  @DisplayName(
  //    "task is ungrabbed, if the latest commit is in FAILURE state"
  //  )
  //  void test1() {
  //    PullRequestEntityStubs
  //        .allPullRequestsExactlyThreeDays(pullrequestEntityRepository);
  //    GithubGraphqlServiceStubs
  //        .failurePullRequestExactlyThreeDays(githubGraphqlService);
  //    TaskEntityRepositoryStubs.grabbedExactlyThreeDaysTask(taskEntityRepository);
  //    ungrabService.call();
  //    Mockito.verify(taskEntityRepository)
  //        .save(TaskEntityTestData.getUngrabExactlyThreeDaysTask());
  //  }
  //
  //  @Test
  //  @DisplayName(
  //    "Task is ungrabed, if the latest SUCCESS commit was pushed 4 days ago and reviewed 4 days ago"
  //  )
  //  void test2() {
  //    PullRequestEntityStubs
  //        .allPullRequestsFourDaysAgo(pullrequestEntityRepository);
  //    GithubGraphqlServiceStubs
  //        .successfullPullRequestAndReviewFourDaysAgo(
  //            githubGraphqlService
  //        );
  //    TaskEntityRepositoryStubs.getGrabbedFourDaysTask(taskEntityRepository);
  //    ungrabService.call();
  //    Mockito.verify(taskEntityRepository)
  //        .save(TaskEntityTestData.getUngrabbedFourDaysTask());
  //  }
  //
  //  @Test
  //  @DisplayName(
  //    "task is ungrabbed, if the latest commit was pushed over 3 days and the status of pull request is SUCCESS and reviewed 3 over days ago"
  //  )
  //  void test3() {
  //    PullRequestEntityStubs
  //        .allPullRequestsOverThreeDays(pullrequestEntityRepository);
  //    GithubGraphqlServiceStubs
  //        .successfullPullRequestAndReviewOverThreeDays(
  //            githubGraphqlService
  //        );
  //    TaskEntityRepositoryStubs.getGrabbedOverThreeDays(taskEntityRepository);
  //    ungrabService.call();
  //    Mockito.verify(taskEntityRepository)
  //        .save(TaskEntityTestData.getUngrabbedTask());
  //  }
  //
  //  @Test
  //  @DisplayName(
  //    "task is ungrabbed, if there is no commit in 3 days since review"
  //  )
  //  void test4() {
  //    PullRequestEntityStubs
  //        .allPullRequestsExactlyThreeDays(pullrequestEntityRepository);
  //    GithubGraphqlServiceStubs
  //        .successfullCommitOverThreeeDaysAndReviewUnderThreeDays(
  //            githubGraphqlService
  //        );
  //    TaskEntityRepositoryStubs.grabbedExactlyThreeDaysTask(taskEntityRepository);
  //    ungrabService.call();
  //    Mockito.verify(taskEntityRepository)
  //        .save(TaskEntityTestData.getUngrabExactlyThreeDaysTask());
  //  }

  //  @Test
  //  @DisplayName(
  //    "task is ungrabbed, if the latest SUCCESS commit was pushed 5 days ago and reviewed 3 days ago"
  //  )
  //  void test5() {
  //    PullRequestEntityStubs
  //        .allPullRequestsOverThreeDays(pullrequestEntityRepository);
  //    GithubGraphqlServiceStubs
  //        .successfullPullRequestOverThreeDaysReviewUnderThreeDays(
  //            githubGraphqlService
  //        );
  //    TaskEntityRepositoryStubs.behaviour(taskEntityRepository);
  //    ungrabService.call();
  //    Mockito.verify(taskEntityRepository, Mockito.times(0))
  //        .save(TaskEntityTestData.getUngrabExactlyThreeDaysTask());
  //  }
}
