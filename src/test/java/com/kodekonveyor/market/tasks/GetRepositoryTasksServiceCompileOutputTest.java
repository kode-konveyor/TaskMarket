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

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("compile output")
@TestedService("GetRepositoryTasksService")
public class GetRepositoryTasksServiceCompileOutputTest
    extends GetRepositoryTasksServiceTestBase {

  @Test
  @DisplayName(
    "TaskEntites are returned successfully"
  )
  void test1() throws JSONException {
    MarketUserEntityRepositoryStubs.behaviour(marketUserEntityRepository);
    assertEquals(
        List.of(TaskEntityTestData.get()),
        getRepositoryTasksService
            .call(GetRepositoryTasksServiceTestData.REPO_NAME)
    );
  }

  @Test
  @DisplayName(
    "Id of TaskEntity are returned successfully"
  )
  void test2() throws JSONException {
    MarketUserEntityRepositoryStubs.behaviour(marketUserEntityRepository);
    assertEquals(
        TaskEntityTestData.get().getId(),
        getRepositoryTasksService
            .call(GetRepositoryTasksServiceTestData.REPO_NAME).get(0).getId()
    );
  }

  @Test
  @DisplayName(
    "Market user of TaskEntity are returned successfully"
  )
  void test3() throws JSONException {
    MarketUserEntityRepositoryStubs.behaviour(marketUserEntityRepository);
    assertEquals(
        TaskEntityTestData.get().getMarketUser(),
        getRepositoryTasksService
            .call(GetRepositoryTasksServiceTestData.REPO_NAME).get(0).getMarketUser()
    );
  }

  @Test
  @DisplayName(
    "Behaviour of TaskEntity are returned successfully"
  )
  void test4() throws JSONException {
    MarketUserEntityRepositoryStubs.behaviour(marketUserEntityRepository);
    assertEquals(
        TaskEntityTestData.get().getBehaviour(),
        getRepositoryTasksService
            .call(GetRepositoryTasksServiceTestData.REPO_NAME).get(0).getBehaviour()
    );
  }

  @Test
  @DisplayName(
    "Description of TaskEntity are returned successfully"
  )
  void test5() throws JSONException {
    MarketUserEntityRepositoryStubs.behaviour(marketUserEntityRepository);
    assertEquals(
        TaskEntityTestData.get().getDescription(),
        getRepositoryTasksService
            .call(GetRepositoryTasksServiceTestData.REPO_NAME).get(0).getDescription()
    );
  }

  @Test
  @DisplayName(
    "Github Id of TaskEntity are returned successfully"
  )
  void test6() throws JSONException {
    MarketUserEntityRepositoryStubs.behaviour(marketUserEntityRepository);
    assertEquals(
        TaskEntityTestData.get().getGithubId(),
        getRepositoryTasksService
            .call(GetRepositoryTasksServiceTestData.REPO_NAME).get(0).getGithubId()
    );
  }

  @Test
  @DisplayName(
    "Service of TaskEntity are returned successfully"
  )
  void test7() throws JSONException {
    MarketUserEntityRepositoryStubs.behaviour(marketUserEntityRepository);
    assertEquals(
        TaskEntityTestData.get().getService(),
        getRepositoryTasksService
            .call(GetRepositoryTasksServiceTestData.REPO_NAME).get(0).getService()
    );
  }
}
