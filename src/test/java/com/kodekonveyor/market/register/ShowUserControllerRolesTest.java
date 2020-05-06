package com.kodekonveyor.market.register;

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
import com.kodekonveyor.authentication.AuthenticatedUserServiceStubs;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("roles")
@TestedService("ShowUserController")
public class ShowUserControllerRolesTest extends ShowUserControllerTestBase {

  @Test
  @DisplayName("Technical user show roles test for DTO")
  public void test1() {
    AuthenticatedUserServiceStubs
        .technicalUser(authenticatedUserService);
    assertEquals(
        MarketUserDTOTestData.getRoleTechnical(), showUserController.call()
    );
  }

  @Test
  @DisplayName("Technical user show roles payment details not visible test")
  public void test2() {
    AuthenticatedUserServiceStubs
        .technicalUser(authenticatedUserService);
    assertEquals(
        MarketUserDTOTestData.getRoleTechnical().getPaymentDetail(),
        showUserController.call().getPaymentDetail()
    );
  }

  @Test
  @DisplayName("Technical user show roles legal name visible test")
  public void test3() {
    AuthenticatedUserServiceStubs
        .technicalUser(authenticatedUserService);
    assertEquals(
        MarketUserDTOTestData.getRoleTechnical().getLegalName(),
        showUserController.call().getLegalName()
    );
  }

  @Test
  @DisplayName("Technical user show roles ID visible test")
  public void test4() {
    AuthenticatedUserServiceStubs
        .technicalUser(authenticatedUserService);
    assertEquals(
        MarketUserDTOTestData.getRoleTechnical().getId(),
        showUserController.call().getId()
    );
  }

  @Test
  @DisplayName("Technical user show roles balance null test")
  public void test5() {
    AuthenticatedUserServiceStubs
        .technicalUser(authenticatedUserService);
    assertEquals(
        MarketUserDTOTestData.getRoleTechnical().getBalanceInCents(),
        showUserController.call().getBalanceInCents()
    );
  }
}
