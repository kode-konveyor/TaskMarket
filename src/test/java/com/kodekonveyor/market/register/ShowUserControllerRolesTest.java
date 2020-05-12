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
  @DisplayName("User details to Technical role returned successfully")
  public void test1() {
    AuthenticatedUserServiceStubs
        .technicalUser(authenticatedUserService);
    assertEquals(
        MarketUserDTOTestData.getRoleTechnical(), showUserController.call()
    );
  }

  @Test
  @DisplayName(
    "User details returned successfully to Technical role without payment details"
  )
  public void test2() {
    AuthenticatedUserServiceStubs
        .technicalUser(authenticatedUserService);
    assertEquals(
        MarketUserDTOTestData.getRoleTechnical().getPaymentDetail(),
        showUserController.call().getPaymentDetail()
    );
  }

  @Test
  @DisplayName(
    "User details returned successfully, with legal name, to Technical role"
  )
  public void test3() {
    AuthenticatedUserServiceStubs
        .technicalUser(authenticatedUserService);
    assertEquals(
        MarketUserDTOTestData.getRoleTechnical().getLegalName(),
        showUserController.call().getLegalName()
    );
  }

  @Test
  @DisplayName(
    "User details  returned successfully, with user id shown, to Technical role"
  )
  public void test4() {
    AuthenticatedUserServiceStubs
        .technicalUser(authenticatedUserService);
    assertEquals(
        MarketUserDTOTestData.getRoleTechnical().getId(),
        showUserController.call().getId()
    );
  }

  @Test
  @DisplayName(
    "User details returned successfully, without balance data, to Technical role "
  )
  public void test5() {
    AuthenticatedUserServiceStubs
        .technicalUser(authenticatedUserService);
    assertEquals(
        MarketUserDTOTestData.getRoleTechnical().getBalanceInCents(),
        showUserController.call().getBalanceInCents()
    );
  }

  @Test
  @DisplayName(
    "User details returned to Contract role user"
  )
  public void test6() {
    AuthenticatedUserServiceStubs
        .kodekonveyorContract(authenticatedUserService);
    assertEquals(
        MarketUserDTOTestData.getRoleKodeKonveyorContract(),
        showUserController.call()
    );
  }

  @Test
  @DisplayName(
    "User details, with payment details, returned to Contract role user"
  )
  public void test7() {
    AuthenticatedUserServiceStubs
        .kodekonveyorContract(authenticatedUserService);
    assertEquals(
        MarketUserDTOTestData.getRoleKodeKonveyorContract().getPaymentDetail(),
        showUserController.call().getPaymentDetail()
    );
  }

  @Test
  @DisplayName(
    "User details, with balance , returned to Contract role user"
  )
  public void test8() {
    AuthenticatedUserServiceStubs
        .kodekonveyorContract(authenticatedUserService);
    assertEquals(
        MarketUserDTOTestData.getRoleKodeKonveyorContract().getBalanceInCents(),
        showUserController.call().getBalanceInCents()
    );
  }

  @Test
  @DisplayName(
    "User details, with contract role ID , returned to Contract role user"
  )
  public void test9() {
    AuthenticatedUserServiceStubs
        .kodekonveyorContract(authenticatedUserService);
    assertEquals(
        MarketUserDTOTestData.getRoleKodeKonveyorContract().getId(),
        showUserController.call().getId()
    );
  }
}
