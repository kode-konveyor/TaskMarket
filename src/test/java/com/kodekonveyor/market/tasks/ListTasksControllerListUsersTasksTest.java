package com.kodekonveyor.market.tasks;

import static org.junit.Assert.assertEquals;

import java.util.List;

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
import com.kodekonveyor.authentication.AuthenticatedUserService2Stubs;
import com.kodekonveyor.authentication.AuthenticatedUserServiceStubs;
import com.kodekonveyor.market.project.ProjectEntityRepositoryStubs;
import com.kodekonveyor.market.register.MarketUserEntityRepositoryStubs;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("")
@TestedService("")
public class ListTasksControllerListUsersTasksTest
    extends ListTasksControllerTestBase {

  @Test
  @DisplayName(
    "user's task list returned successfully"
  )
  void test() {
    AuthenticatedUserService2Stubs
        .privateProjectCoder(authenticatedUserService);
    MarketUserEntityRepositoryStubs
        .memberOfPrivateProject(marketUserEntityRepository);
    TaskEntityRepositoryStubs.behaviour(taskEntityRepository);
    ProjectEntityRepositoryStubs.behaviour(projectEntityRepository);

    assertEquals(
        List.of(
            TaskEntityTestData.getPrivateProjectInProgressTask(),
            TaskEntityTestData.getUpForGrab(),
            TaskEntityTestData.getUpForGrab(),
            TaskEntityTestData.getClosedTask()
        ), listTasksController.call()
    );
  }

  @Test
  @DisplayName(
    "if user is not a memeber of private projects, up for grab tasks related to those projects are not added to list"
  )
  void test1() {
    AuthenticatedUserServiceStubs.authenticated(authenticatedUserService);
    MarketUserEntityRepositoryStubs
        .behaviour(marketUserEntityRepository);
    TaskEntityRepositoryStubs.behaviour(taskEntityRepository);
    ProjectEntityRepositoryStubs.behaviour(projectEntityRepository);

    assertEquals(
        List.of(
            TaskEntityTestData.getInProgressPublicProject(),
            TaskEntityTestData.getUpForGrab(),
            TaskEntityTestData.getClosedTaskPublicProject()
        ), listTasksController.call()
    );
  }

  @Test
  @DisplayName(
    "if user is not a market user,only up for grab tasks of public projects listed"
  )
  void test2() {
    AuthenticatedUserServiceStubs.authenticated(authenticatedUserService);
    MarketUserEntityRepositoryStubs
        .nonMarketUser(marketUserEntityRepository);
    ProjectEntityRepositoryStubs.behaviour(projectEntityRepository);

    assertEquals(
        List.of(
            TaskEntityTestData.getUpForGrab()
        ), listTasksController.call()
    );
  }
}
