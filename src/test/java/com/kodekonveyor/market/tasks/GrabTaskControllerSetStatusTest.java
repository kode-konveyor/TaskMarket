package com.kodekonveyor.market.tasks;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.kodekonveyor.annotations.TestedBehaviour;
import com.kodekonveyor.annotations.TestedService;
import com.kodekonveyor.exception.ThrowableTester;
import com.kodekonveyor.market.project.ProjectDTOTestData;
import com.kodekonveyor.market.register.MarketUserEntityTestData;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("set status")
@TestedService("GrabTaskController")
public class GrabTaskControllerSetStatusTest
    extends GrabTaskControllerTestBase {

  @Test
  @DisplayName(
    "if the Task status is up for grab, set the status to in progress"
  )
  public void testSetStatus() {

    grabTaskController.call(TaskTestData.ID);
    final ArgumentCaptor<TaskEntity> entity =
        ArgumentCaptor.forClass(TaskEntity.class);
    verify(taskEntityRepository).save(entity.capture());
    assertEquals(
        entity.getValue().getStatus(), TaskStatusEnum.IN_PROGRESS
    );

  }

  @Test
  @DisplayName(
    "if the Task status is up for grab, the user is assigned for the task"
  )
  public void testUserAssigned() {
    grabTaskController.call(TaskTestData.ID_2);
    final ArgumentCaptor<TaskEntity> entity1 =
        ArgumentCaptor.forClass(TaskEntity.class);
    verify(taskEntityRepository).save(entity1.capture());
    assertEquals(
        entity1.getValue().getMarketUser(), MarketUserEntityTestData.get()
    );

  }

  @Test
  @DisplayName(
    "Task is updated on github after task to user assignment successfully"
  )
  public void testTaskUpdatedOnGithub() {
    grabTaskController.call(TaskTestData.ID);
    verify(updateGithubIssueService)
        .call(TaskEntityTestData.getTaskWithStatusUpdated());
  }

  @Test
  @DisplayName(
    "Task is saved successfully after all updates"
  )
  public void testTaskSaved() {
    grabTaskController.call(TaskTestData.ID);
    verify(taskEntityRepository)
        .save(TaskEntityTestData.getTaskWithStatusUpdated());
  }

  @Test
  @DisplayName(
    "If a given task is not in Up for grab state, an exception is thrown"
  )
  public void testIncorrectTaskStatus() {
    ThrowableTester.assertThrows(
        () -> grabTaskController.call(TaskTestData.ID_IN_PROGRESS)
    ).assertMessageIs(TaskTestData.TASK_NOT_UP_FOR_GRAB);
  }

  @Test
  @DisplayName(
    "Check number of up for grab tasks in the project"
  )
  public void testUpForGrabServiceCall() {
    grabTaskController.call(TaskTestData.ID);
    verify(checkUpforgrabTasksService)
        .call(ProjectDTOTestData.getUrlAndPullRequest());
  }

}
