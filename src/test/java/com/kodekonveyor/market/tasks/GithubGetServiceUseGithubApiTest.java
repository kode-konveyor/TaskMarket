package com.kodekonveyor.market.tasks;

import static org.junit.Assert.assertEquals;

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
import com.kodekonveyor.market.register.MarketUserEntityRepositoryStubs;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("Get tasks from github")
@TestedService("GetRepositoryTasksService")
public class GithubGetServiceUseGithubApiTest
    extends GetRepositoryTasksServiceTestBase {

  @Test
  @DisplayName(
    "Task's ID extracted successfully"
  )
  void test1() throws JSONException {
    final String taskId = githubRequest
        .call(GetRepositoryTasksServiceTestData.REPO_NAME).getJSONObject(GetRepositoryTasksServiceTestData.INDEX)
        .getString(GetRepositoryTasksServiceTestData.ID);
    assertEquals(
        GetRepositoryTasksServiceTestData.TASK_ID, taskId

    );
  }

  @Test
  @DisplayName(
    "Task's behaviour is extracted successfully"
  )
  void test2() throws JSONException {
    final String value[] = githubRequest
        .call(GetRepositoryTasksServiceTestData.REPO_NAME).getJSONObject(GetRepositoryTasksServiceTestData.INDEX)
        .getString(GetRepositoryTasksServiceTestData.TITLE).split(GetRepositoryTasksServiceTestData.FRONTSLASH);
    assertEquals(
        GetRepositoryTasksServiceTestData.BEHAVIOUR,
        value[GetRepositoryTasksServiceTestData.ONE]

    );
  }

  @Test
  @DisplayName(
    "Task's service is extracted successfully"
  )
  void test3() throws JSONException {

    final String value[] = githubRequest
        .call(GetRepositoryTasksServiceTestData.REPO_NAME).getJSONObject(GetRepositoryTasksServiceTestData.INDEX)
        .getString(GetRepositoryTasksServiceTestData.TITLE).split(GetRepositoryTasksServiceTestData.FRONTSLASH);
    assertEquals(
        GetRepositoryTasksServiceTestData.SERVICE,
        value[GetRepositoryTasksServiceTestData.ZERO]

    );
  }

  @Test
  @DisplayName(
    "Task's Description extracted successfully"
  )
  void test4() throws JSONException {

    final String description = githubRequest
        .call(GetRepositoryTasksServiceTestData.REPO_NAME).getJSONObject(GetRepositoryTasksServiceTestData.INDEX)
        .getString(GetRepositoryTasksServiceTestData.BODY).toString();
    assertEquals(
        GetRepositoryTasksServiceTestData.DESCRIPTION, description

    );
  }

  @Test
  @DisplayName(
    "Task's Github issue Id extracted successfully"
  )
  void test6() throws JSONException {

    final String issueId = githubRequest
        .call(GetRepositoryTasksServiceTestData.REPO_NAME).getJSONObject(GetRepositoryTasksServiceTestData.INDEX)
        .getString(GetRepositoryTasksServiceTestData.NUMBER).toString();
    assertEquals(
        GetRepositoryTasksServiceTestData.GITHUB_ID, issueId

    );
  }

  @Test
  @DisplayName(
    "Task's Markert user Id extracted successfully"
  )
  void test7() throws JSONException {

    final String marketUserId = githubRequest
        .call(GetRepositoryTasksServiceTestData.REPO_NAME).getJSONObject(GetRepositoryTasksServiceTestData.INDEX)
        .getJSONObject(GetRepositoryTasksServiceTestData.USER).getString(GetRepositoryTasksServiceTestData.ID);
    assertEquals(
        GetRepositoryTasksServiceTestData.MARKET_USER_ID, marketUserId

    );
  }

  @Test
  @DisplayName(
    "Tasks are saved successfuly"
  )
  void test8() throws JSONException {
    MarketUserEntityRepositoryStubs.behaviour(marketUserEntityRepository);
    getRepositoryTasksService.call(GetRepositoryTasksServiceTestData.REPO_NAME);

    Mockito.verify(taskEntityRepository, Mockito.times(1))
        .save(TaskEntityTestData.get());

  }
}
