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
        TaskEntityTestData.getNotInModelTaskUpdatedDescrition(),
        updateTasksService.call(TaskEntityTestData.get())
    );
  }

  @Test
  @DisplayName(
    "Only description tag are updated if, the task has same service, behaviour and documentation"
  )
  void test1() throws JSONException {
    TaskEntityRepositoryStubs.behaviour(taskEntityRepository);
    assertEquals(
        TaskEntityTestData.getTaskUpdatedDescrition(),
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
        TaskEntityTestData.getDifferentDescriptionUpdated(),
        updateTasksService.call(TaskEntityTestData.getDifferentDescription())
    );
  }

  @Test
  @DisplayName(
    "if the description between delimiter is same as task descrition, nothing is done"
  )
  void test2() throws JSONException {
    TaskEntityRepositoryStubs.delimiterDescription(taskEntityRepository);
    assertEquals(
        TaskEntityTestData.getTaskUpdatedDescrition(),
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
        TaskEntityTestData.getDifferentDescriptionUpdated(),
        updateTasksService.call(TaskEntityTestData.getDifferentDescription())
    );

  }

  @Test
  @DisplayName(
    "If the task has same behaviour as github issue but different service, then it is created."
  )
  void test4() {
    TaskEntityRepositoryStubs.taskNotinRepository(taskEntityRepository);
    assertEquals(
        TaskEntityTestData.getDifferentServiceTaskUpdatedDescrition(),
        updateTasksService.call(TaskEntityTestData.getDifferentServiceTask())

    );
  }

  @Test
  @DisplayName(
    "If the task same service as github issue but different behaviour, then it is created."
  )
  void test5() {
    TaskEntityRepositoryStubs.taskNotinRepository(taskEntityRepository);
    assertEquals(
        TaskEntityTestData.getDifferentBehaviourTaskUpdatedDescrition(),
        updateTasksService.call(TaskEntityTestData.getDifferentBehaviourTask())

    );
  }
}
