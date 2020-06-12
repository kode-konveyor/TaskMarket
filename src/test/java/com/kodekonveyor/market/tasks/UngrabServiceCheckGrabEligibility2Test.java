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
    "Nothing is done, if the difference between latest SUCCESSFULL commit and review is less than 3 days"
  )
  void test() {
    PullRequestEntityStubs
        .behaviour(pullrequestEntityRepository);
    GithubGraphqlServiceStubs
        .successfullReviewAndCommitUnderThreeDays(githubGraphqlService);
    TaskEntityRepositoryStubs.behaviour(taskEntityRepository);
    ungrabService.call();
    Mockito.verify(taskEntityRepository, Mockito.times(0))
        .save(TaskEntityTestData.getUngrabPullRequestIssuedtask());
  }

  @Test
  @DisplayName(
    "Task is ungrabbed, if the difference between latest SUCCESSFULL commit and review is equal to 4 days"
  )
  void test1() {
    PullRequestEntityStubs
        .behaviour(pullrequestEntityRepository);
    GithubGraphqlServiceStubs
        .successfullReviewAndCommitEqualToFourDays(githubGraphqlService);
    TaskEntityRepositoryStubs.behaviour(taskEntityRepository);
    ungrabService.call();
    Mockito.verify(taskEntityRepository)
        .save(TaskEntityTestData.getUngrabPullRequestIssuedtask());
  }

  @Test
  @DisplayName(
    "Task is ungrabbed, if the difference between latest SUCCESSFULL commit and review is more than 3 days"
  )
  void test2() {
    PullRequestEntityStubs
        .behaviour(pullrequestEntityRepository);
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
  void test4() {
    PullRequestEntityStubs
        .behaviour(pullrequestEntityRepository);
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
        .behaviour(pullrequestEntityRepository);
    GithubGraphqlServiceStubs
        .failureCommitEqualLessThanThreeDays(githubGraphqlService);
    TaskEntityRepositoryStubs.behaviour(taskEntityRepository);
    ungrabService.call();
    Mockito.verify(taskEntityRepository, Mockito.times(0))
        .save(TaskEntityTestData.getUngrabPullRequestIssuedtask());
  }

}
