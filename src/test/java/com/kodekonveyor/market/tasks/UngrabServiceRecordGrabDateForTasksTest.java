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
@TestedBehaviour("record grab date for tasks")
@TestedService("UngrabService")
public class UngrabServiceRecordGrabDateForTasksTest
    extends UngrabServiceTestBase {

  @Test
  @DisplayName(
    "clear date when the task is ungrabbed"
  )
  void test() {
    TaskEntityRepositoryStubs.grabbedOverThreeDays(taskEntityRepository);
    PullRequestEntityStubs.pullRequestNotIssued(pullrequestEntityRepository);
    ungrabService.call();
    Mockito.verify(taskEntityRepository)
        .save(TaskEntityStatusesTestData.getStatusUngrabbed());
  }

}
