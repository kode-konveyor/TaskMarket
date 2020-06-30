package com.kodekonveyor.market.tasks;

import java.util.List;

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
import com.kodekonveyor.authentication.AuthenticatedUserService2Stubs;
import com.kodekonveyor.authentication.UserEntityTestData;
import com.kodekonveyor.logging.LoggingMarkerConstants;
import com.kodekonveyor.market.project.ProjectEntityRepositoryStubs;
import com.kodekonveyor.market.register.MarketUserEntityRepositoryStubs;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("Logging")
@TestedService("ListTasksController")
public class ListTasksControllerLoggingTest
    extends ListTasksControllerTestBase {

  @Test
  @DisplayName("The call of the service is logged")
  void test() {
    AuthenticatedUserService2Stubs
        .privateProjectCoder(authenticatedUserService);
    MarketUserEntityRepositoryStubs
        .memberOfPrivateProject(marketUserEntityRepository);
    TaskEntityRepositoryStubs.behaviour(taskEntityRepository);
    ProjectEntityRepositoryStubs.behaviour(projectEntityRepository);
    listTasksController.call();
    Mockito.verify(logger).info(
        LoggingMarkerConstants.TASK,
        UserEntityTestData.getPrivateProjectCoder().getId().toString()
    );
  }

  @Test
  @DisplayName("return of tasks is logged")
  void test2() {
    AuthenticatedUserService2Stubs
        .privateProjectCoder(authenticatedUserService);
    MarketUserEntityRepositoryStubs
        .memberOfPrivateProject(marketUserEntityRepository);
    TaskEntityRepositoryStubs.behaviour(taskEntityRepository);
    ProjectEntityRepositoryStubs.behaviour(projectEntityRepository);
    listTasksController.call();

    Mockito.verify(logger).debug(
        LoggingMarkerConstants.TASK,
        TaskTestData.TASKS_RETURNED_SUCCESSFULLY + List.of(
            TaskEntityTestData.getPrivateProjectInProgressTask().getId(),
            TaskEntityTestData.getUpForGrab().getId(),
            TaskEntityTestData.getUpForGrab().getId(),
            TaskEntityTestData.getClosedTask().getId()
        )
    );
  }
}
