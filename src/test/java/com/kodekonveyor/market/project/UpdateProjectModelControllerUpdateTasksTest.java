package com.kodekonveyor.market.project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
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
import com.kodekonveyor.authentication.AuthenticatedUserServiceStubs;
import com.kodekonveyor.market.tasks.TaskEntityTestData;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("update tasks")
@TestedService("UpdateProjectModelController")
public class UpdateProjectModelControllerUpdateTasksTest
    extends UpdateTaskServiceTestBase {

  @Override
  @BeforeEach
  void setUp() {
    super.setUp();
    AuthenticatedUserServiceStubs.projectManager(authenticatedUserService);
  }

  @Test
  @DisplayName("If there is no github issue with the name, then it is created.")
  public void test() {
    updateTasksService
        .call(ModelExcerptDTOTestData.getDifferentTaskName().getTasks());
    Mockito.verify(taskEntityRepository)
        .save(TaskEntityTestData.getDifferentBehaviour());
  }

  @Test
  @DisplayName("The task with updated documentation is saved successfully")
  public void test1() {
    updateTasksService
        .call(ModelExcerptDTOTestData.getNewDescription().getTasks());
    Mockito.verify(taskEntityRepository)
        .save(TaskEntityTestData.getNewDescription());
  }

  @Test
  @DisplayName("The documentation of the task is updated successfully")
  public void test2() {
    updateTasksService
        .call(ModelExcerptDTOTestData.getNewDescription().getTasks());
    assertEquals(
        ModelExcerptDTOTestData.getNewDescription().getTasks()
            .getDescription(),
        TaskEntityTestData.getNewDescription().getDescription()
    );
  }

  @Test
  @DisplayName(
    "if the documentation of the task is the same as the documentation of the github ,Then nothing is done "
  )
  public void test3() {
    updateTasksService
        .call(ModelExcerptDTOTestData.get().getTasks());
    assertEquals(
        ModelExcerptDTOTestData.get().getTasks()
            .getDescription(),
        TaskEntityTestData.get().getDescription()
    );
  }
}
