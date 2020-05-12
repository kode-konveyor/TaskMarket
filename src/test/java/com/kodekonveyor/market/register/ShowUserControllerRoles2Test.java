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
public class ShowUserControllerRoles2Test extends ShowUserControllerTestBase {

  @Test
  @DisplayName(
    "User details, with contract role user, returned to Contract role user"
  )
  public void test10() {
    AuthenticatedUserServiceStubs
        .kodekonveyorContract(authenticatedUserService);
    assertEquals(
        MarketUserDTOTestData.getRoleKodeKonveyorContract().getUser(),
        showUserController.call().getUser()
    );
  }

  @Test
  @DisplayName(
    "User details, with users legal name, returned to Contract role user"
  )
  public void test11() {
    AuthenticatedUserServiceStubs
        .kodekonveyorContract(authenticatedUserService);
    assertEquals(
        MarketUserDTOTestData.getRoleKodeKonveyorContract().getLegalName(),
        showUserController.call().getLegalName()
    );
  }

  @Test
  @DisplayName(
    "User details, with users legal form, returned to Contract role user"
  )
  public void test12() {
    AuthenticatedUserServiceStubs
        .kodekonveyorContract(authenticatedUserService);
    assertEquals(
        MarketUserDTOTestData.getRoleKodeKonveyorContract().getLegalForm(),
        showUserController.call().getLegalForm()
    );
  }

  @Test
  @DisplayName(
    "User details, with users legal address, returned to Contract role user"
  )
  public void test13() {
    AuthenticatedUserServiceStubs
        .kodekonveyorContract(authenticatedUserService);
    assertEquals(
        MarketUserDTOTestData.getRoleKodeKonveyorContract().getLegalAddress(),
        showUserController.call().getLegalAddress()
    );
  }

  @Test
  @DisplayName(
    "User details, returned to project manager role user"
  )
  public void test14() {
    AuthenticatedUserServiceStubs.projectManager(authenticatedUserService);
    assertEquals(
        MarketUserDTOTestData.getRoleProjectManager(),
        showUserController.call()
    );
  }

  @Test
  @DisplayName(
    "User details, with payment details,  returned to project manager role user"
  )
  public void test15() {
    AuthenticatedUserServiceStubs.projectManager(authenticatedUserService);
    assertEquals(
        MarketUserDTOTestData.getRoleProjectManager().getPaymentDetail(),
        showUserController.call().getPaymentDetail()
    );
  }

  @Test
  @DisplayName(
    "User details, with balance,  returned to project manager role user"
  )
  public void test16() {
    AuthenticatedUserServiceStubs.projectManager(authenticatedUserService);
    assertEquals(
        MarketUserDTOTestData.getRoleProjectManager().getBalanceInCents(),
        showUserController.call().getBalanceInCents()
    );
  }

  @Test
  @DisplayName(
    "User details, with project manager id,  returned to project manager role user"
  )
  public void test17() {
    AuthenticatedUserServiceStubs.projectManager(authenticatedUserService);
    assertEquals(
        MarketUserDTOTestData.getRoleProjectManager().getId(),
        showUserController.call().getId()
    );
  }

  @Test
  @DisplayName(
    "User details, with project manager user,  returned to project manager role user"
  )
  public void test18() {
    AuthenticatedUserServiceStubs.projectManager(authenticatedUserService);
    assertEquals(
        MarketUserDTOTestData.getRoleProjectManager().getUser(),
        showUserController.call().getUser()
    );
  }

  @Test
  @DisplayName(
    "User details, with legal name,  returned to project manager role user"
  )
  public void test19() {
    AuthenticatedUserServiceStubs.projectManager(authenticatedUserService);
    assertEquals(
        MarketUserDTOTestData.getRoleProjectManager().getLegalName(),
        showUserController.call().getLegalName()
    );
  }
}
