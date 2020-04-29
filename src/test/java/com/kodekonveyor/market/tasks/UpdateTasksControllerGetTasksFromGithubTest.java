package com.kodekonveyor.market.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONException;
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
import com.kodekonveyor.market.project.ProjectEntityTestData;
import com.kodekonveyor.market.register.MarketUserEntityTestData;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("Get tasks from github")
@TestedService("UpdateTasksController")
public class UpdateTasksControllerGetTasksFromGithubTest
    extends UpdateTasksControllerTestBase {

  @Test
  @DisplayName(
    "Task entity is saved successfully"
  )
  void test1() throws JSONException {
    updateTasksController.call();

    Mockito.verify(taskEntityRepository).save(TaskEntityTestData.get());
  }

  @Test
  @DisplayName(
    "Task Id is saved sucessfully"
  )
  void test2() throws JSONException {
    updateTasksController.call();
    assertEquals(
        TaskDToTestData.get().getGithubId(),
        TaskEntityTestData.get().getGithubId()
    );
  }

  @Test
  @DisplayName(
    "Task name is saved sucessfully"
  )
  void test3() throws JSONException {
    updateTasksController.call();
    assertEquals(
        TaskDToTestData.get().getName(), TaskEntityTestData.get().getName()
    );
  }

  @Test
  @DisplayName(
    "Project of the task is saved sucessfully"
  )
  void test4() throws JSONException {
    updateTasksController.call();
    assertEquals(
        ProjectEntityTestData.get(), TaskEntityTestData.get().getProject()
    );
  }

  @Test
  @DisplayName(
    "Owner of the task is saved sucessfully"
  )
  void test5() throws JSONException {
    updateTasksController.call();
    assertEquals(
        MarketUserEntityTestData.get(),
        TaskEntityTestData.get().getResponsible()
    );
  }

  @Test
  @DisplayName(
    "Status of the task is saved sucessfully"
  )
  void test6() throws JSONException {
    updateTasksController.call();
    assertEquals(
        TaskDToTestData.get().getStatus(), TaskEntityTestData.get().getStatus()
    );
  }

}
