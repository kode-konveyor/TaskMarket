package com.kodekonveyor.market.register;

import static com.kodekonveyor.authentication.UserTestData.LOGIN;
import static org.junit.Assert.assertEquals;

import org.junit.Assert;
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

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("Roles")
@TestedService("ShowUserController")
public class ShowUserControllerRoles1Test extends ShowUserControllerTestBase {

  @Test
  @DisplayName(
    "Market User Id is returned successfully, when session user has any role."
  )
  public void test1() {
    AuthenticatedUserService2Stubs.technicalUser(authenticatedUserService);
    assertEquals(
        MarketUserDTOTestData.get().getId(),
        showUserController.call(LOGIN).getId()
    );
  }

  @Test
  @DisplayName(
    "User Id is returned successfully, when session user has any role."
  )
  public void test2() {
    AuthenticatedUserService2Stubs.technicalUser(authenticatedUserService);
    assertEquals(
        MarketUserDTOTestData.get().getUser(),
        showUserController.call(LOGIN).getUser()
    );
  }

  @Test
  @DisplayName(
    "Github username is returned successfully, when session user has any role."
  )
  public void test3() {
    AuthenticatedUserService2Stubs.technicalUser(authenticatedUserService);
    assertEquals(
        MarketUserDTOTestData.get().getLogin(),
        showUserController.call(LOGIN).getLogin()
    );
  }

  @Test
  @DisplayName(
    "Legal name is returned successfully, when session user has role - technical."
  )
  public void test4() {
    AuthenticatedUserService2Stubs.technicalUser(authenticatedUserService);
    assertEquals(
        MarketUserDTOTestData.get().getLegalName(),
        showUserController.call(LOGIN).getLegalName()
    );
  }

  @Test
  @DisplayName(
    "BalanceInCents is not returned, when session user has role - technical."
  )
  public void test5() {
    AuthenticatedUserService2Stubs.technicalUser(authenticatedUserService);
    Assert.assertNull(showUserController.call(LOGIN).getBalanceInCents());
  }

  @Test
  @DisplayName(
    "LegalForm is not returned, when session user has role - technical."
  )
  public void test6() {
    AuthenticatedUserService2Stubs.technicalUser(authenticatedUserService);
    Assert.assertNull(showUserController.call(LOGIN).getLegalForm());
  }

  @Test
  @DisplayName(
    "LegalAddress is not returned, when session user has role - technical."
  )
  public void test7() {
    AuthenticatedUserService2Stubs.technicalUser(authenticatedUserService);
    Assert.assertNull(showUserController.call(LOGIN).getLegalAddress());
  }

  @Test
  @DisplayName(
    "IsTermsAccepted is not returned, when session user has role - technical."
  )
  public void test8() {
    AuthenticatedUserService2Stubs.technicalUser(authenticatedUserService);
    Assert.assertNull(showUserController.call(LOGIN).getIsTermsAccepted());
  }

  @Test
  @DisplayName(
    "PersonalName is not returned, when session user has role - technical."
  )
  public void test9() {
    AuthenticatedUserService2Stubs.technicalUser(authenticatedUserService);
    Assert.assertNull(showUserController.call(LOGIN).getPersonalName());
  }

  @Test
  @DisplayName("Email is not returned, when session user has role - technical.")
  public void test10() {
    AuthenticatedUserService2Stubs.technicalUser(authenticatedUserService);
    Assert.assertNull(showUserController.call(LOGIN).getEmail());
  }

}
