package com.kodekonveyor.market.project;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
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
import com.kodekonveyor.exception.ThrowableTester;
import com.kodekonveyor.market.register.MarketUserEntityRepositoryStubs;
import com.kodekonveyor.market.register.MarketUserTestData;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("budget")
@TestedService("AddFundsToProjectController")
public class AddFundsToProjectControllerBudgetTest2
    extends AddFundsToProjectControllerTestBase {

  @BeforeEach
  public void setUpBefore() {
    MarketUserEntityRepositoryStubs.behaviour2(marketUserEntityRepository);
  }

  @Test
  @DisplayName(
    "User is Project manager with the negative balance Amount. "
  )
  public void testNegativeBalance() {
    AuthenticatedUserServiceStubs.projectManager(authenticatedUserService);

    assertTrue(
        addFundsToProjectController.call(
            ProjectTestData.ID_BUDGET,
            MarketUserTestData.BALANCE_FOR_NEGATIVE_OUTCOME
        ).getBudgetInCents() < 0
    );
  }

  @Test
  @DisplayName(
    "When the user's balance is zero, an exception is thrown"
  )
  public void testZeroUserBalance() {
    AuthenticatedUserServiceStubs
        .authenticatedIdForZeroBalance(authenticatedUserService);

    ThrowableTester.assertThrows(
        () -> addFundsToProjectController.call(
            ProjectTestData.ID_ADD_FUNDS,
            MarketUserTestData.BALANCE_IN_CENTS
        )
    ).assertMessageIs(
        ProjectTestData.BALANCE_IS_NEGATIVE + ProjectTestData.COMMA +
            ProjectTestData.USER_NOT_MANAGER
    );
  }

  @Test
  @DisplayName(
    "When the user's balance is negative, exception is thrown"
  )
  public void testNegativeUserBalance() {
    AuthenticatedUserServiceStubs
        .authenticatedIdForNegativeBalance(authenticatedUserService);

    ThrowableTester.assertThrows(
        () -> addFundsToProjectController.call(
            ProjectTestData.ID_ADD_FUNDS,
            MarketUserTestData.BALANCE_IN_CENTS
        )
    ).assertMessageIs(
        ProjectTestData.BALANCE_IS_NEGATIVE + ProjectTestData.COMMA +
            ProjectTestData.USER_NOT_MANAGER
    );
  }

  @Test
  @DisplayName(
    "When the user's balance is less than the budget in cents, exception is thrown"
  )
  public void testLessUserBalance() {

    ThrowableTester.assertThrows(
        () -> addFundsToProjectController.call(
            ProjectTestData.ID_ADD_FUNDS,
            MarketUserTestData.BALANCE_IN_CENTS
        )
    ).assertMessageIs(
        ProjectTestData.USER_BALANCE_IS_LESS_THAN_THE_BUDGET
    );
  }

}
