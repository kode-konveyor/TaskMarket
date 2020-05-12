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
public class ShowUserControllerRoles3Test extends ShowUserControllerTestBase {

  @Test
  @DisplayName(
    "User details, with legal form,  returned to project manager role user"
  )
  public void test20() {
    AuthenticatedUserServiceStubs.projectManager(authenticatedUserService);
    assertEquals(
        MarketUserDTOTestData.getRoleProjectManager().getLegalForm(),
        showUserController.call().getLegalForm()
    );
  }

  @Test
  @DisplayName(
    "User details, with email,  returned to project manager role user"
  )
  public void test21() {
    AuthenticatedUserServiceStubs.projectManager(authenticatedUserService);
    assertEquals(
        MarketUserDTOTestData.getRoleProjectManager().getEmail(),
        showUserController.call().getEmail()
    );
  }

  @Test
  @DisplayName(
    "User details, with term acceptance status ,  returned to project manager role user"
  )
  public void test22() {
    AuthenticatedUserServiceStubs.projectManager(authenticatedUserService);
    assertEquals(
        MarketUserDTOTestData.getRoleProjectManager().getIsTermsAccepted(),
        showUserController.call().getIsTermsAccepted()
    );
  }

  @Test
  @DisplayName(
    "Entire User details returned to herself"
  )
  public void test23() {
    AuthenticatedUserServiceStubs.authenticated(authenticatedUserService);
    assertEquals(
        MarketUserDTOTestData.get(),
        showUserController.call()
    );
  }

  @Test
  @DisplayName(
    "Entire User details, with payment details, returned to herself"
  )
  public void test24() {
    AuthenticatedUserServiceStubs.authenticated(authenticatedUserService);
    assertEquals(
        MarketUserDTOTestData.get().getPaymentDetail(),
        showUserController.call().getPaymentDetail()
    );
  }

  @Test
  @DisplayName(
    "Entire User details, with balance, returned to herself"
  )
  public void test25() {
    AuthenticatedUserServiceStubs.authenticated(authenticatedUserService);
    assertEquals(
        MarketUserDTOTestData.get().getBalanceInCents(),
        showUserController.call().getBalanceInCents()
    );
  }

  @Test
  @DisplayName(
    "Entire User details, with email, returned to herself"
  )
  public void test26() {
    AuthenticatedUserServiceStubs.authenticated(authenticatedUserService);
    assertEquals(
        MarketUserDTOTestData.get().getEmail(),
        showUserController.call().getEmail()
    );
  }
}
