package com.kodekonveyor.market.project;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
import com.kodekonveyor.authentication.AuthenticatedUserService2Stubs;
import com.kodekonveyor.authentication.AuthenticatedUserServiceStubs;
import com.kodekonveyor.exception.ThrowableTester;
import com.kodekonveyor.market.register.MarketUserEntityRepositoryStubs;
import com.kodekonveyor.market.register.MarketUserTestData;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("budget")
@TestedService("AddFundsToProjectController")
public class AddFundsToProjectControllerBudget2Test
    extends AddFundsToProjectControllerTestBase {

  @BeforeEach
  public void setUpBefore() {
    MarketUserEntityRepositoryStubs.behaviour2(marketUserEntityRepository);
  }

  @Test
  @DisplayName(
    "User is Project manager with the updated balance Amount as negative. "
  )
  public void testNegativeBalance() {
    AuthenticatedUserServiceStubs.projectManager(authenticatedUserService);

    assertTrue(
        addFundsToProjectController.call(
            ProjectTestData.ID_BUDGET,
            MarketUserTestData.NEGATIVE_BALANCE
        ).getBudgetInCents() > 0
    );
  }

  @Test
  @DisplayName(
    "When input budget in cents is zero, project budget and user balance remains unchanged. "
  )
  public void testForZeroBudgetIncents() {
    AuthenticatedUserServiceStubs.projectManager(authenticatedUserService);

    assertTrue(
        addFundsToProjectController.call(
            ProjectTestData.ID_BUDGET,
            MarketUserTestData.ZERO_BALANCE
        ).getBudgetInCents() > 0
    );
  }

  @Test
  @DisplayName(
    "When User is Project manager with the balance amount as zero, an exception is thrown ."
  )
  public void testForProjectManager() {
    AuthenticatedUserService2Stubs
        .forProjectManagerForZeroBalance(authenticatedUserService);
    ThrowableTester.assertThrows(
        () -> addFundsToProjectController.call(
            ProjectTestData.ID_BUDGET,
            MarketUserTestData.BALANCE_IN_CENTS
        )
    ).assertMessageIs(ProjectTestData.USER_BALANCE_IS_LESS_THAN_THE_BUDGET);

  }

  @Test
  @DisplayName(
    "When the user's balance is equal to budget in cents, project budget is updated correctly"
  )
  public void testEqualUserBalance() {
    AuthenticatedUserServiceStubs
        .projectManager(authenticatedUserService);
    assertEquals(
        addFundsToProjectController.call(
            ProjectDTOTestData.getAddFunds().getId(),
            MarketUserTestData.BALANCE_IN_CENTS
        ).getBudgetInCents(),
        ProjectDTOTestData.getAddFunds().getBudgetInCents() +
            MarketUserTestData.BALANCE_IN_CENTS
    );

  }

}
