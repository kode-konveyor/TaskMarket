package com.kodekonveyor.market.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
@TestedBehaviour("Get tasks from github")
@TestedService("GetRepositoryTasksService")
public class GetRepositoryTasksServiceCompileOutputTest
    extends GetRepositoryTasksServiceTestBase {

  @Test
  @DisplayName(
    "JSON tasks count returned successfuly"
  )
  void test() throws JSONException {
    assertEquals(
        GetRepositoryTasksServiceTestData.TASK_COUNT,
        githubRequest
            .call(GetRepositoryTasksServiceTestData.REPO_NAME).length()

    );
  }

  @Test
  @DisplayName(
    "Project for task is returned successfully"
  )
  void test10() throws JSONException {

    assertEquals(
        GetRepositoryTasksServiceTestData.TASK_OWNER2, getRepositoryTasksService
            .call(GetRepositoryTasksServiceTestData.REPO_NAME).get(GetRepositoryTasksServiceTestData.INDEX).getResponsible()
    );
  }

  @Test
  @DisplayName(
    "Task with 'in progress' status is returned successfully"
  )
  void test11() throws JSONException {

    assertEquals(
        TaskStatusEnum.IN_PROGRESS, getRepositoryTasksService
            .call(GetRepositoryTasksServiceTestData.REPO_NAME).get(GetRepositoryTasksServiceTestData.INDEX3).getStatus()
    );
  }

  @Test
  @DisplayName(
    "Task with 'up for grab' status is returned successfully"
  )
  void test12() throws JSONException {

    assertEquals(
        TaskStatusEnum.UP_FOR_GRAB, getRepositoryTasksService
            .call(GetRepositoryTasksServiceTestData.REPO_NAME).get(GetRepositoryTasksServiceTestData.INDEX2).getStatus()
    );
  }

  @Test
  @DisplayName(
    "Task with 'open' status is returned successfully"
  )
  void test13() throws JSONException {

    assertEquals(
        TaskStatusEnum.OPEN, getRepositoryTasksService
            .call(GetRepositoryTasksServiceTestData.REPO_NAME).get(GetRepositoryTasksServiceTestData.INDEX4).getStatus()
    );
  }

  @Test
  @DisplayName(
    "Tasks ID  is returned successfully"
  )
  void test7() throws JSONException {
    assertEquals(
        GetRepositoryTasksServiceTestData.TASK_ID, getRepositoryTasksService
            .call(GetRepositoryTasksServiceTestData.REPO_NAME).get(GetRepositoryTasksServiceTestData.INDEX).getGithubId()
    );
  }
}
