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
public class ShowUserControllerRoles4Test extends ShowUserControllerTestBase {

  @Test
  @DisplayName(
    "Entire User details, with Id, returned to herself"
  )
  public void test27() {
    AuthenticatedUserServiceStubs.authenticated(authenticatedUserService);
    assertEquals(
        MarketUserDTOTestData.get().getId(),
        showUserController.call().getId()
    );
  }

  @Test
  @DisplayName(
    "Entire User details, with legal name, returned to herself"
  )
  public void test28() {
    AuthenticatedUserServiceStubs.authenticated(authenticatedUserService);
    assertEquals(
        MarketUserDTOTestData.get().getLegalName(),
        showUserController.call().getLegalName()
    );
  }

  @Test
  @DisplayName(
    "Entire User details, with legal address, returned to herself"
  )
  public void test29() {
    AuthenticatedUserServiceStubs.authenticated(authenticatedUserService);
    assertEquals(
        MarketUserDTOTestData.get().getLegalAddress(),
        showUserController.call().getLegalAddress()
    );
  }

  @Test
  @DisplayName(
    "Entire User details, with legal form, returned to herself"
  )
  public void test30() {
    AuthenticatedUserServiceStubs.authenticated(authenticatedUserService);
    assertEquals(
        MarketUserDTOTestData.get().getLegalForm(),
        showUserController.call().getLegalForm()
    );
  }

  @Test
  @DisplayName(
    "Entire User details, with user, returned to herself"
  )
  public void test31() {
    AuthenticatedUserServiceStubs.authenticated(authenticatedUserService);
    assertEquals(
        MarketUserDTOTestData.get().getUser(),
        showUserController.call().getUser()
    );
  }
}
