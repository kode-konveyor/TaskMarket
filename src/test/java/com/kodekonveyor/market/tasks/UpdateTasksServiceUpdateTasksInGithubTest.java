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

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("update tasks in github")
@TestedService("UpdateTasksService")
public class UpdateTasksServiceUpdateTasksInGithubTest
    extends UpdateTasksServiceTestBase {

  @Test
  @DisplayName(
    "Newly created task is updated on GitHub"
  )
  void test() {
    TaskEntityRepositoryStubs.taskNotinRepository(taskEntityRepository);
    updateTasksService.call(TaskEntityTestData.get());
    Mockito.verify(updateGithubIssueService)
        .call(TaskEntityDescriptionsTestData.getDescriptionUpdatedNotInModel());

  }

  @Test
  @DisplayName(
    "After updating the descrition task is updated on Github"
  )
  void test1() {
    TaskEntityRepositoryStubs.behaviour(taskEntityRepository);
    updateTasksService
        .call(TaskEntityDescriptionsTestData.getDescriptionDifferent());
    Mockito.verify(updateGithubIssueService)
        .call(
            TaskEntityDescriptionsTestData.getDescriptionDifferentUpdated()
        );
  }

}
