package com.kodekonveyor.market.project;

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
import com.kodekonveyor.exception.ThrowableTester;
import com.kodekonveyor.market.register.MarketUserEntityRepositoryStubs;
import com.kodekonveyor.market.register.MarketUserEntityTestData;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("budget")
@TestedService("CreateProjectController")
public class CreateProjectControllerBudgetTest
    extends CreateProjectControllerTestBase {

  @Test
  @DisplayName(
    "If the account balance of the user is less than project budget, exception is thrown"
  )
  public void test() {
    AuthenticatedUserServiceStubs.projectManager(authenticatedUserService);
    MarketUserEntityRepositoryStubs.behaviour(marketUserEntityRepository);
    ThrowableTester.assertThrows(
        () -> createProjectController
            .call(ProjectDTOTestData.get())
    )

        .assertMessageIs(
            CreateProjectControllerTestData.BALANCE_LESS_THAN_USER_BUDGET
        );

  }

  @Test
  @DisplayName(
    "If the account balance of the user is equal to project budget, no exception is thrown"
  )
  public void test1() {
    AuthenticatedUserServiceStubs.projectManager(authenticatedUserService);
    MarketUserEntityRepositoryStubs
        .userBalanceEqualToBudget(marketUserEntityRepository);
    ThrowableTester.assertNoException(
        () -> createProjectController
            .call(ProjectDTOTestData.get())
    );
  }

  @Test
  @DisplayName(
    "If the account balance of the user is more than project budget, no exception is thrown"
  )
  public void test2() {
    AuthenticatedUserServiceStubs.projectManager(authenticatedUserService);
    MarketUserEntityRepositoryStubs
        .userBalanceMoreThanBudget(marketUserEntityRepository);
    ThrowableTester.assertNoException(
        () -> createProjectController
            .call(ProjectDTOTestData.get())
    );
  }

  @Test
  @DisplayName(
    "After budget manipulations, Market user with updated balance is saved successfully in repository"
  )
  public void test3() {
    AuthenticatedUserServiceStubs.projectManager(authenticatedUserService);
    MarketUserEntityRepositoryStubs
        .userBalanceMoreThanBudget(marketUserEntityRepository);
    createProjectController.call(ProjectDTOTestData.get());
    Mockito.verify(marketUserEntityRepository)
        .save(MarketUserEntityTestData.getBalanceUpdatedMarketUser());
  }

}
