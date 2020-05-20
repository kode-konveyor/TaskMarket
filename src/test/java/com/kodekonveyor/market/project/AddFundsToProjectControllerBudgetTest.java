package com.kodekonveyor.market.project;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
import com.kodekonveyor.exception.ThrowableTester;
import com.kodekonveyor.market.register.MarketUserDTOTestData;
import com.kodekonveyor.market.register.MarketUserTestData;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("budget")
@TestedService("AddFundsToProjectController")
public class AddFundsToProjectControllerBudgetTest
    extends AddFundsToProjectControllerTestBase {

  @Test
  @DisplayName(
    "The given budget should be greater than equal to the account balance of the user. "
  )
  public void test() {

    addFundsToProjectController.call(
        ProjectTestData.ID_ADD_FUNDS,
        MarketUserTestData.LESS_BALANCE
    );

    assertTrue(
        ProjectDTOTestData
            .getUrl()
            .getBudgetInCents() > MarketUserTestData.LESS_BALANCE
    );
  }

  @Test
  @DisplayName(
    "User's balance and budget in cents is same. "
  )
  public void testEqualUserBalanceAndBudget() {
    addFundsToProjectController.call(
        ProjectTestData.ID_ADD_FUNDS,
        MarketUserTestData.EQUAL_BALANCE
    );

    assertEquals(
        MarketUserDTOTestData.get().getBalanceInCents(),
        MarketUserTestData.EQUAL_BALANCE
    );
  }

  @Test
  @DisplayName(
    "After budget in cents added to the project budget and if that amount is negative, an exception is thrown. "
  )
  public void testUpdatedNegativeBalance() {

    ThrowableTester.assertThrows(
        () -> addFundsToProjectController.call(
            ProjectTestData.ID_BUDGET,
            MarketUserTestData.NEGATIVE_BALANCE2
        )
    ).assertMessageIs(
        ProjectTestData.INVALID_PROJECT_BUDGET_AMOUNT
    );

  }
}