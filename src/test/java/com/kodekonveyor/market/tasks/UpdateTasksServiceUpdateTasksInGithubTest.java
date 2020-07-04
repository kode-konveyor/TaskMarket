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

  @Test
  @DisplayName(
    "if the description between delimiter is same as task descrition, nothing is done"
  )
  void test2() {
    TaskEntityRepositoryStubs.delimiterDescription(taskEntityRepository);
    updateTasksService.call(TaskEntityTestData.get());
    Mockito.verify(updateGithubIssueService)
        .call(
            TaskEntityDescriptionsTestData.getDescritionUpdated()
        );
  }

  @Test
  @DisplayName(
    "if the description between delimiter is different from the task description,it is updated and task is updated on Github"
  )
  void test3() {
    TaskEntityRepositoryStubs.delimiterDescription(taskEntityRepository);
    updateTasksService
        .call(TaskEntityDescriptionsTestData.getDescriptionDifferent());
    Mockito.verify(updateGithubIssueService)
        .call(TaskEntityDescriptionsTestData.getDescriptionDifferentUpdated());

  }

  @Test
  @DisplayName(
    "if description is different from the input task description and the START delimiter is not at the beginning,it is updated and task is updated on Github"
  )
  void test4() {
    TaskEntityRepositoryStubs
        .delimiterNotAtStartDesctiptionTask(taskEntityRepository);
    updateTasksService
        .call(TaskEntityDescriptionsTestData.getDescriptionDelimiterNotAtStart());
    Mockito.verify(updateGithubIssueService)
        .call(
            TaskEntityDescriptionsTestData
                .getDescriptionUpdatedDelimiterAtStart()
        );
  }

  @Test
  @DisplayName(
    "if description is different from the input task description and there is no END delimiter,it is updated and task is updated on Github"
  )
  void test5() {
    TaskEntityRepositoryStubs
        .getupdatedDescriptionNoEndDelimiter(taskEntityRepository);
    updateTasksService
        .call(TaskEntityDescriptionsTestData.getDescriptionDifferent());
    Mockito.verify(updateGithubIssueService)
        .call(
            TaskEntityDescriptionsTestData.getDescriptionDifferentUpdated()
        );
  }

  @Test
  @DisplayName(
    "if description is different from the input task description and there is no START delimiter,it is updated and task is updated on Github"
  )
  void test6() {
    TaskEntityRepositoryStubs
        .getupdatedDescriptionNoStartDelimiter(taskEntityRepository);
    updateTasksService
        .call(TaskEntityDescriptionsTestData.getDescriptionDifferent());
    Mockito.verify(updateGithubIssueService)
        .call(
            TaskEntityDescriptionsTestData.getDescriptionDifferentUpdated()
        );

  }

  @Test
  @DisplayName(
    "If the task has same behaviour as github issue but different service, then it is created and task is updated on Github."
  )
  void test7() {
    TaskEntityRepositoryStubs.taskNotinRepository(taskEntityRepository);
    updateTasksService.call(TaskEntityTestData.getServiceDifferent());
    Mockito.verify(updateGithubIssueService)
        .call(
            TaskEntityDescriptionsTestData
                .getDescritionUpdatedDifferentService()
        );
  }

  @Test
  @DisplayName(
    "If the task same service as github issue but different behaviour, then it is created and task is updated on Github."
  )
  void test8() {
    TaskEntityRepositoryStubs.taskNotinRepository(taskEntityRepository);
    updateTasksService.call(TaskEntityTestData.getBehaviourDifferent());
    Mockito.verify(updateGithubIssueService)
        .call(
            TaskEntityDescriptionsTestData
                .getDescritionUpdatedDifferentBehaviour()
        );
  }

}
