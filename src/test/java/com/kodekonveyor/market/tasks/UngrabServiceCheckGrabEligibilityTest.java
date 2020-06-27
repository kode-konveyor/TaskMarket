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

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("Check grab eligibility")
@TestedService("UngrabService")
public class UngrabServiceCheckGrabEligibilityTest
    extends UngrabServiceTestBase {

  @Test
  @DisplayName(
    "A task is ungrabbed if task has been grabbed for more than 3 days without a pull request"
  )
  void test() {
    TaskEntityRepositoryStubs.grabbedOverThreeDays(taskEntityRepository);
    PullRequestEntityStubs.pullRequestNotIssued(pullrequestEntityRepository);
    ungrabService.call();
    Mockito.verify(taskEntityRepository)
        .save(TaskEntityTestData.getStatusUngrabbed());
  }

  @Test
  @DisplayName(
    "Nothing is done if task has been grabbed for more than 3 days and pull request is issued"
  )
  void test1() {
    TaskEntityRepositoryStubs.grabbedOverThreeDays(taskEntityRepository);
    PullRequestEntityStubs.pullRequestIssued(pullrequestEntityRepository);
    ungrabService.call();
    Mockito.verify(taskEntityRepository, Mockito.times(0))
        .save(TaskEntityTestData.getStatusUngrabbed());
  }

  @Test
  @DisplayName(
    "Nothing is done if task has been grabbed for exactly 3 days without a pull request"
  )
  void test2() {
    TaskEntityRepositoryStubs.grabbedExactlyThreeDays(taskEntityRepository);
    PullRequestEntityStubs.pullRequestNotIssued(pullrequestEntityRepository);
    ungrabService.call();
    Mockito.verify(taskEntityRepository, Mockito.times(0))
        .save(TaskEntityTestData.getStatusUngrabbed());
  }

  @Test
  @DisplayName(
    "Nothing is done if task has been grabbed for exactly 3 days and pull request is issued"
  )
  void test3() {
    TaskEntityRepositoryStubs.grabbedExactlyThreeDays(taskEntityRepository);
    PullRequestEntityStubs.pullRequestIssued(pullrequestEntityRepository);
    ungrabService.call();
    Mockito.verify(taskEntityRepository, Mockito.times(0))
        .save(TaskEntityTestData.getStatusUngrabbed());
  }

  @Test
  @DisplayName(
    "A task is ungrabbed if task has been grabbed for 4 days without a pull request"
  )
  void test4() {
    TaskEntityRepositoryStubs.grabbedForFourDays(taskEntityRepository);
    PullRequestEntityStubs.pullRequestNotIssued(pullrequestEntityRepository);
    ungrabService.call();
    Mockito.verify(taskEntityRepository)
        .save(TaskEntityTestData.getStatusUngrabbed());
  }

  @Test
  @DisplayName(
    "Nothing is done if task has been grabbed for 4 days and pull request is issued"
  )
  void test5() {
    TaskEntityRepositoryStubs.grabbedForFourDays(taskEntityRepository);
    PullRequestEntityStubs.pullRequestIssued(pullrequestEntityRepository);
    ungrabService.call();
    Mockito.verify(taskEntityRepository, Mockito.times(0))
        .save(TaskEntityTestData.getStatusUngrabbed());
  }
}
