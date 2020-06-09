package com.kodekonveyor.market.project;

import static org.junit.Assert.assertEquals;

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
import com.kodekonveyor.authentication.AuthenticatedUserServiceStubs;
import com.kodekonveyor.market.register.MarketUserEntityRepositoryStubs;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("compile output")
@TestedService("CreateProjectController")
public class CreateProjectControllerCompileOutputTest
    extends CreateProjectControllerTestBase {

  @Test
  @DisplayName("The project id is returned successfully")
  public void test() {
    AuthenticatedUserServiceStubs.projectManager(authenticatedUserService);
    MarketUserEntityRepositoryStubs
        .userBalanceMoreThanBudget(marketUserEntityRepository);
    final ProjectDTO ret =
        createProjectController.call(ProjectDTOTestData.get());
    assertEquals(
        ProjectTestData.ID, ret.getId()
    );
  }

  @Test
  @DisplayName("The project name is returned successfully")
  public void test1() {
    AuthenticatedUserServiceStubs.projectManager(authenticatedUserService);
    MarketUserEntityRepositoryStubs
        .userBalanceMoreThanBudget(marketUserEntityRepository);

    assertEquals(
        ProjectTestData.NAME,
        createProjectController.call(ProjectDTOTestData.get()).getName()
    );
  }

  @Test
  @DisplayName("The controller returns project successfully")
  void test3() {
    AuthenticatedUserServiceStubs.projectManager(authenticatedUserService);
    MarketUserEntityRepositoryStubs
        .userBalanceMoreThanBudget(marketUserEntityRepository);
    final ProjectDTO ret =
        createProjectController.call(ProjectDTOTestData.get());
    assertEquals(

        ret, ProjectDTOTestData.get()
    );
  }
}
