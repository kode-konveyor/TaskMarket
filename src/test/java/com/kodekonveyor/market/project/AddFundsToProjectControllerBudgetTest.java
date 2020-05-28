package com.kodekonveyor.market.project;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

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
import com.kodekonveyor.market.register.MarketUserDTOTestData;
import com.kodekonveyor.market.register.MarketUserEntityRepositoryStubs;
import com.kodekonveyor.market.register.MarketUserEntityTestData;
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

    assertTrue(
        addFundsToProjectController.call(
            ProjectDTOTestData.getAddFunds().getId(),
            MarketUserTestData.LESS_BALANCE
        ).getBudgetInCents() > MarketUserTestData.LESS_BALANCE
    );

  }

  @Test
  @DisplayName(
    "User's balance and budget in cents is same. "
  )
  public void testEqualUserBalanceAndBudget() {

    addFundsToProjectController.call(
        ProjectDTOTestData.getAddFunds().getId(),
        MarketUserDTOTestData.get().getBalanceInCents()
    );
    assertEquals(
        MarketUserDTOTestData.get().getBalanceInCents(),
        MarketUserTestData.BALANCE_IN_CENTS
    );
  }

  @Test
  @DisplayName(
    "If budget in cents is negative and more than the project's balance, an exception is thrown. "
  )
  public void testNegativeBudgetInput() {
    ThrowableTester.assertThrows(
        () -> addFundsToProjectController.call(
            ProjectTestData.ID_BUDGET,
            MarketUserTestData.NEGATIVE_BALANCE2
        )
    ).assertMessageIs(
        ProjectTestData.BALANCE_IS_NEGATIVE
    );
  }

  @Test
  @DisplayName(
    "If project balance and input budget is equal but opposite amount, project balance becomes zero. "
  )
  public void testEqualBudget() {

    assertEquals(
        addFundsToProjectController.call(
            ProjectTestData.ID_BUDGET,
            MarketUserTestData.NEGATIVE_BALANCE3
        ).getBudgetInCents(), 0
    );

  }

  @Test
  @DisplayName(
    "Updated market user is saved successfully"
  )
  public void testSaveMarketUserEntity() {
    AuthenticatedUserServiceStubs.projectManager(authenticatedUserService);
    MarketUserEntityRepositoryStubs.behaviour2(marketUserEntityRepository);
    addFundsToProjectController.call(
        ProjectEntityTestData.getAddFunds().getId(),
        MarketUserTestData.LESS_BALANCE
    );
    verify(marketUserEntityRepository)
        .save(MarketUserEntityTestData.getUpdatedUserBalance());
  }

  @Test
  @DisplayName(
    "Updated Project Entity is saved successfully"
  )
  public void testSaveProjectEntity() {
    addFundsToProjectController.call(
        ProjectEntityTestData.getAddFunds().getId(),
        MarketUserTestData.LESS_BALANCE
    );
    verify(projectEntityRepository)
        .save(ProjectEntityTestData.getUpdatedAddFunds());
  }

}
