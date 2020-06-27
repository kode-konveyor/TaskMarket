package com.kodekonveyor.market.tasks;

import static org.junit.Assert.assertEquals;

import org.json.JSONException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.kodekonveyor.annotations.TestedBehaviour;
import com.kodekonveyor.annotations.TestedService;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("update tasks")
@TestedService("UpdateTasksService")
public class UpdateTasksServiceUpdateTasksTest
    extends UpdateTasksServiceTestBase {

  @Test
  @DisplayName(
    "If there is no github issue with the service/behaviour name, it is created."
  )
  void test() throws JSONException {
    TaskEntityRepositoryStubs.taskNotinRepository(taskEntityRepository);
    assertEquals(
        TaskEntityDescriptionsTestData.getDescriptionUpdatedNotInModel(),
        updateTasksService.call(TaskEntityTestData.get())
    );
  }

  @Test
  @DisplayName(
    "if the description of repository task is different from the task description,it is updated"
  )
  void test7() throws JSONException {
    TaskEntityRepositoryStubs.behaviour(taskEntityRepository);
    assertEquals(
        TaskEntityDescriptionsTestData.getDescriptionDifferentUpdated(),
        updateTasksService
            .call(TaskEntityDescriptionsTestData.getDescriptionDifferent())
    );
  }

  @Test
  @DisplayName(
    "if the description between delimiter is same as task descrition, nothing is done"
  )
  void test2() throws JSONException {
    TaskEntityRepositoryStubs.delimiterDescription(taskEntityRepository);
    assertEquals(
        TaskEntityDescriptionsTestData.getDescritionUpdated(),
        updateTasksService.call(TaskEntityTestData.get())
    );

  }

  @Test
  @DisplayName(
    "if the description between delimiter is different from the task description,it is updated"
  )
  void test6() throws JSONException {
    TaskEntityRepositoryStubs.delimiterDescription(taskEntityRepository);
    assertEquals(
        TaskEntityDescriptionsTestData.getDescriptionDifferentUpdated(),
        updateTasksService
            .call(TaskEntityDescriptionsTestData.getDescriptionDifferent())
    );

  }

  @Test
  @DisplayName(
    "if description is different from the input task description and the START delimiter is not at the beginning,it is updated"
  )
  void test8() throws JSONException {
    TaskEntityRepositoryStubs
        .delimiterNotAtStartDesctiptionTask(taskEntityRepository);
    assertEquals(
        TaskEntityDescriptionsTestData
            .getDescriptionUpdatedDelimiterAtStart(),
        updateTasksService
            .call(
                TaskEntityDescriptionsTestData
                    .getDescriptionDelimiterNotAtStart()
            )
    );

  }

  @Test
  @DisplayName(
    "if description is different from the input task description and there is no END delimiter,it is updated"
  )
  void test9() throws JSONException {
    TaskEntityRepositoryStubs
        .getupdatedDescriptionNoEndDelimiter(taskEntityRepository);
    assertEquals(
        TaskEntityDescriptionsTestData.getDescriptionDifferentUpdated(),
        updateTasksService
            .call(
                TaskEntityDescriptionsTestData.getDescriptionDifferent()
            )
    );

  }

  @Test
  @DisplayName(
    "if description is different from the input task description and there is no START delimiter,it is updated"
  )
  void test10() throws JSONException {
    TaskEntityRepositoryStubs
        .getupdatedDescriptionNoStartDelimiter(taskEntityRepository);
    assertEquals(
        TaskEntityDescriptionsTestData.getDescriptionDifferentUpdated(),
        updateTasksService
            .call(
                TaskEntityDescriptionsTestData.getDescriptionDifferent()
            )
    );

  }

  @Test
  @DisplayName(
    "If the task has same behaviour as github issue but different service, then it is created."
  )
  void test4() {
    TaskEntityRepositoryStubs.taskNotinRepository(taskEntityRepository);
    assertEquals(
        TaskEntityDescriptionsTestData.getDescritionUpdatedDifferentService(),
        updateTasksService.call(TaskEntityTestData.getServiceDifferent())

    );
  }

  @Test
  @DisplayName(
    "If the task same service as github issue but different behaviour, then it is created."
  )
  void test5() {
    TaskEntityRepositoryStubs.taskNotinRepository(taskEntityRepository);
    assertEquals(
        TaskEntityDescriptionsTestData.getDescritionUpdatedDifferentBehaviour(),
        updateTasksService.call(TaskEntityTestData.getBehaviourDifferent())

    );
  }
}
