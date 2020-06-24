package com.kodekonveyor.market.register;

import static org.junit.Assert.assertEquals;

import com.kodekonveyor.authentication.UserTestData;
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
@TestedBehaviour("compile output")
@TestedService("ShowUserController")
public class ShowUserControllerCompileOutputTest
    extends ShowUserControllerTestBase {

  @Test
  @DisplayName("The Market user details are returned successfully")
  public void test() {
    AuthenticatedUserServiceStubs
        .authenticated(authenticatedUserService);
    assertEquals(MarketUserDTOTestData.get(), showUserController.call(UserTestData.LOGIN));
  }

  @Test
  @DisplayName("The Market user's Id is returned successfully")
  public void test1() {
    AuthenticatedUserServiceStubs
        .authenticated(authenticatedUserService);
    assertEquals(
        MarketUserDTOTestData.get().getId(), showUserController.call(UserTestData.LOGIN).getId()
    );
  }

  @Test
  @DisplayName("The Market user's balance is returned successfully")
  public void test2() {
    AuthenticatedUserServiceStubs
        .authenticated(authenticatedUserService);
    assertEquals(
        MarketUserDTOTestData.get().getBalanceInCents(),
        showUserController.call(UserTestData.LOGIN).getBalanceInCents()
    );
  }

  @Test
  @DisplayName("The Market user's Email Id is returned successfully")
  public void test3() {
    AuthenticatedUserServiceStubs
        .authenticated(authenticatedUserService);
    assertEquals(
        MarketUserDTOTestData.get().getEmail(),
        showUserController.call(UserTestData.LOGIN).getEmail()
    );
  }

  @Test
  @DisplayName(
    "The Market user's contract acceptance status is returned successfully"
  )
  public void test4() {
    AuthenticatedUserServiceStubs
        .authenticated(authenticatedUserService);
    assertEquals(
        MarketUserDTOTestData.get().getIsTermsAccepted(),
        showUserController.call(UserTestData.LOGIN).getIsTermsAccepted()
    );
  }

  @Test
  @DisplayName("The Market user's legal address is returned successfully")
  public void test5() {
    AuthenticatedUserServiceStubs
        .authenticated(authenticatedUserService);
    assertEquals(
        MarketUserDTOTestData.get().getLegalAddress(),
        showUserController.call(UserTestData.LOGIN).getLegalAddress()
    );
  }

  @Test
  @DisplayName("The Market user's legal name is returned successfully")
  public void test6() {
    AuthenticatedUserServiceStubs
        .authenticated(authenticatedUserService);
    assertEquals(
        MarketUserDTOTestData.get().getLegalName(),
        showUserController.call(UserTestData.LOGIN).getLegalName()
    );
  }

}
