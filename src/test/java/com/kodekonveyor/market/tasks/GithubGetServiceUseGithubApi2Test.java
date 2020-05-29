package com.kodekonveyor.market.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

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
import com.kodekonveyor.market.register.MarketUserEntityRepositoryStubs;
import com.kodekonveyor.market.register.MarketUserEntityTestData;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("compile output")
@TestedService("GetRepositoryTasksService")
public class GithubGetServiceUseGithubApi2Test
    extends GetRepositoryTasksServiceTestBase {

  @Test
  @DisplayName(
    "Task's Github issue Id is saved successfuly"
  )
  void test7() throws JSONException {

    MarketUserEntityRepositoryStubs.behaviour(marketUserEntityRepository);
    getRepositoryTasksService.call(GetRepositoryTasksServiceTestData.REPO_NAME);
    assertEquals(
        Long.parseLong(GetRepositoryTasksServiceTestData.GITHUB_ID),
        TaskEntityTestData.get().getGithubId()
    );

  }

  @Test
  @DisplayName(
    "Task's Market user is saved successfuly"
  )
  void test8() throws JSONException {

    MarketUserEntityRepositoryStubs.behaviour(marketUserEntityRepository);
    getRepositoryTasksService.call(GetRepositoryTasksServiceTestData.REPO_NAME);
    assertEquals(
        MarketUserEntityTestData.get(), TaskEntityTestData.get().getMarketUser()
    );

  }

  @Test
  @DisplayName(
    "Task's behaviour is saved successfuly"
  )
  void test9() throws JSONException {

    MarketUserEntityRepositoryStubs.behaviour(marketUserEntityRepository);
    getRepositoryTasksService.call(GetRepositoryTasksServiceTestData.REPO_NAME);
    assertEquals(
        GetRepositoryTasksServiceTestData.BEHAVIOUR,
        TaskEntityTestData.get().getBehaviour()
    );

  }

  @Test
  @DisplayName(
    "Task's service is saved successfuly"
  )
  void test10() throws JSONException {

    MarketUserEntityRepositoryStubs.behaviour(marketUserEntityRepository);
    getRepositoryTasksService.call(GetRepositoryTasksServiceTestData.REPO_NAME);
    assertEquals(
        GetRepositoryTasksServiceTestData.SERVICE,
        TaskEntityTestData.get().getService()
    );

  }

  @Test
  @DisplayName(
    "Task's Description is saved successfuly"
  )
  void test11() throws JSONException {

    MarketUserEntityRepositoryStubs.behaviour(marketUserEntityRepository);
    getRepositoryTasksService.call(GetRepositoryTasksServiceTestData.REPO_NAME);
    assertEquals(
        GetRepositoryTasksServiceTestData.DESCRIPTION,
        TaskEntityTestData.get().getDescription()
    );

  }

  @Test
  @DisplayName(
    "Task's Id is saved successfuly"
  )
  void test12() throws JSONException {

    MarketUserEntityRepositoryStubs.behaviour(marketUserEntityRepository);
    getRepositoryTasksService.call(GetRepositoryTasksServiceTestData.REPO_NAME);
    assertEquals(
        Long.parseLong(GetRepositoryTasksServiceTestData.TASK_ID),
        TaskEntityTestData.get().getId()
    );
  }

  @Test
  @DisplayName(
    "Task entities are returned successfully"
  )
  void test13() throws JSONException {

    MarketUserEntityRepositoryStubs.behaviour(marketUserEntityRepository);
    assertEquals(
        List.of(TaskEntityTestData.get(), TaskEntityTestData.getOtherTask()),
        getRepositoryTasksService
            .call(GetRepositoryTasksServiceTestData.REPO_NAME)
    );
  }

}
