package com.kodekonveyor.authentication;


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

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("authenticated user")
@TestedService("AuthenticatedUserService")
public class AuthenticatedUserServiceAuthenticatedUserTest
    extends AuthenticatedUserServiceTestBase {

  @Test
  @DisplayName("Gets the user by the authenticated name")
  public void test() {
    AuthenticationStubs.authenticated();
    assertEquals(
        UserEntityTestData.get(), authenticatedUserService.call()
    );
  }

  @Test
  @DisplayName(
    "When authentication object is null, we throw NotLoggedInException"
  )
  public void test2() {
    AuthenticationStubs.nullAuthentication();
    ThrowableTester.assertThrows(() -> authenticatedUserService.call())
        .assertException(NotLoggedInException.class);
  }

  @Test
  @DisplayName(
    "When authentication object is null, the message is 'No Authentication'"
  )
  public void test3() {
    AuthenticationStubs.nullAuthentication();
    ThrowableTester.assertThrows(() -> authenticatedUserService.call())
        .assertMessageIs(AuthenticatedUserServiceTestData.NO_AUTHENTICATION);
  }

  @Test
  @DisplayName(
    "When returned credential is null, we throw NotLoggedInException"
  )
  public void test4() {
    AuthenticationStubs.nullCredential();
    ThrowableTester.assertThrows(() -> authenticatedUserService.call())
        .assertException(NotLoggedInException.class);
  }

  @Test
  @DisplayName(
    "When returned credential is null, the exception message is 'No Credential'"
  )
  public void test5() {
    AuthenticationStubs.nullCredential();
    ThrowableTester.assertThrows(() -> authenticatedUserService.call())
        .assertMessageIs(AuthenticatedUserServiceTestData.NO_CREDENTIAL);
  }

  @Test
  @DisplayName(
    "When there is no user for the authentication, we throw NotLoggedInException."
  )
  public void test7() {
    AuthenticationStubs.badAuthenticated();
    ThrowableTester.assertThrows(() -> authenticatedUserService.call())
        .assertException(NotLoggedInException.class);
  }

  @Test
  @DisplayName(
    "When there is no user for the authentication, the exception message is 'This should not happen'"
  )
  public void test8() {
    AuthenticationStubs.badAuthenticated();
    ThrowableTester.assertThrows(() -> authenticatedUserService.call())
        .assertMessageIs(AuthenticatedUserServiceTestData.SHOULD_NOT_HAPPEN);
  }

}
