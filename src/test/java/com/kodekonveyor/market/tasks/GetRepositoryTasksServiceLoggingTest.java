package com.kodekonveyor.market.tasks;

import java.util.List;

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
import com.kodekonveyor.authentication.UserEntityRepositoryStubs;
import com.kodekonveyor.market.register.MarketUserEntityRepositoryStubs;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("Logging")
@TestedService("GetRepositoryTasksService")
public class GetRepositoryTasksServiceLoggingTest
    extends GetRepositoryTasksServiceTestBase {

  @Test
  @DisplayName("The call of the service is logged")
  void test() throws JSONException {
    UserEntityRepositoryStubs.behaviour(userEntityRepository);
    MarketUserEntityRepositoryStubs.behaviour(marketUserEntityRepository);
    getRepositoryTasksService.call(GetRepositoryTasksServiceTestData.REPO_NAME);
    Mockito.verify(loggerService).info(
        GetRepositoryTasksServiceTestData.TASK,
        GetRepositoryTasksServiceTestData.TASK_RECEIVED +
            GetRepositoryTasksServiceTestData.REPO_NAME
    );
  }

  @Test
  @DisplayName("Task entity save operation logged successfully")
  void test1() throws JSONException {
    UserEntityRepositoryStubs.behaviour(userEntityRepository);
    MarketUserEntityRepositoryStubs.behaviour(marketUserEntityRepository);
    getRepositoryTasksService.call(GetRepositoryTasksServiceTestData.REPO_NAME);
    Mockito.verify(loggerService).debug(
        GetRepositoryTasksServiceTestData.TASK,
        GetRepositoryTasksServiceTestData.ENTITIES_SAVED_SUCCESSFULLY +
            List.of(
                GetRepositoryTasksServiceTestData.TASK_ID,
                GetRepositoryTasksServiceTestData.TASK_ID1
            )
    );
  }
}
