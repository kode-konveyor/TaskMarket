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
import org.skyscreamer.jsonassert.JSONAssert;

import com.kodekonveyor.annotations.TestedBehaviour;
import com.kodekonveyor.annotations.TestedService;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("Get tasks from github")
@TestedService("GetRepositoryTasksService")
public class GetRepositoryTasksServiceGetTasksFromGithubTest
    extends GetRepositoryTasksServiceTestBase {

  @Test
  @DisplayName(
    "Tasks ID extracted successfuly"
  )
  void test1() throws JSONException {
    final String taskId = githubRequest
        .call(GetRepositoryTasksServiceTestData.REPO_NAME).getJSONObject(GetRepositoryTasksServiceTestData.INDEX)
        .getString(GetRepositoryTasksServiceTestData.ID).toString();
    assertEquals(
        GetRepositoryTasksServiceTestData.TASK_ID, taskId

    );
  }

  @Test
  @DisplayName(
    "Tasks name extracted successfuly"
  )
  void test2() throws JSONException {

    final String value = githubRequest
        .call(GetRepositoryTasksServiceTestData.REPO_NAME).getJSONObject(GetRepositoryTasksServiceTestData.INDEX)
        .getString(GetRepositoryTasksServiceTestData.TITLE);
    assertEquals(
        GetRepositoryTasksServiceTestData.TASK_NAME2, value

    );
  }

  @Test
  @DisplayName(
    "Tasks owner extracted successfuly"
  )
  void test4() throws JSONException {
    final String responsible = githubRequest
        .call(GetRepositoryTasksServiceTestData.REPO_NAME).getJSONObject(GetRepositoryTasksServiceTestData.INDEX).getJSONObject(
            GetRepositoryTasksServiceTestData.USER
        ).getString(GetRepositoryTasksServiceTestData.LOGIN);
    assertEquals(
        GetRepositoryTasksServiceTestData.TASK_OWNER2, responsible

    );
  }

  @Test
  @DisplayName(
    "The JSON array is fetched successfully"
  )
  void test5() throws JSONException {
    JSONAssert.assertEquals(
        GetRepositoryTasksServiceTestData.get(),
        githubRequest.call(GetRepositoryTasksServiceTestData.REPO_NAME), true
    );
  }

  @Test
  @DisplayName(
    "all the DTOs are returned successfully"
  )
  void test6() throws JSONException {

    assertEquals(
        GetRepositoryTasksServiceTestData.TASK_COUNT, getRepositoryTasksService
            .call(GetRepositoryTasksServiceTestData.REPO_NAME).size()
    );
  }

  @Test
  @DisplayName(
    "Tasks name is returned successfully"
  )
  void test8() throws JSONException {

    assertEquals(
        GetRepositoryTasksServiceTestData.TASK_NAME2, getRepositoryTasksService
            .call(GetRepositoryTasksServiceTestData.REPO_NAME).get(GetRepositoryTasksServiceTestData.INDEX).getName()
    );
  }

  @Test
  @DisplayName(
    "Project for task is returned successfully"
  )
  void test9() throws JSONException {

    assertEquals(
        GetRepositoryTasksServiceTestData.REPO_NAME, getRepositoryTasksService
            .call(GetRepositoryTasksServiceTestData.REPO_NAME).get(GetRepositoryTasksServiceTestData.INDEX).getProject()
    );
  }
}
