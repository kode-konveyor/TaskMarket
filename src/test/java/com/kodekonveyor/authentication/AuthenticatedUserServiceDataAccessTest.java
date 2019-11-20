package com.kodekonveyor.authentication;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
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
@TestedBehaviour("Data access")
@TestedService("AuthenticatedUserService")
public class AuthenticatedUserServiceDataAccessTest
    extends AuthenticatedUserServiceTestBase {

  private ThrowableTester tester;

  @Override
  @BeforeEach
  public void setUp() {
    super.setUp();
    tester = new ThrowableTester();
  }

  @Test
  @DisplayName("Gets the user by the authenticated name")
  public void test() {
    AuthenticationStubs.authenticated(userTestData);
    assertEquals(
        userTestData.TEST_USER_ENTITY, authenticatedUserService.call()
    );
  }

  @Test
  @DisplayName(
    "When authentication object is null, we throw NotLoggedInException"
  )
  public void test2() {
    AuthenticationStubs.nullAuthentication();
    final ThrowableTester tester = new ThrowableTester();
    tester.assertThrows(() -> authenticatedUserService.call())
        .assertException(NotLoggedInException.class);
  }

  @Test
  @DisplayName(
    "When authentication object is null, the message is 'No Authentication'"
  )
  public void test3() {
    AuthenticationStubs.nullAuthentication();
    final ThrowableTester tester = new ThrowableTester();
    tester.assertThrows(() -> authenticatedUserService.call())
        .assertMessageIs(userTestData.NO_AUTHENTICATION);
  }

  @Test
  @DisplayName(
    "When returned credential is null, we throw NotLoggedInException"
  )
  public void test4() {
    AuthenticationStubs.nullCredential();
    tester.assertThrows(() -> authenticatedUserService.call())
        .assertException(NotLoggedInException.class);
  }

  @Test
  @DisplayName(
    "When returned credential is null, the exception message is 'No Credential'"
  )
  public void test5() {
    AuthenticationStubs.nullCredential();
    tester.assertThrows(() -> authenticatedUserService.call())
        .assertMessageIs(userTestData.NO_CREDENTIAL);
  }

  @Test
  @DisplayName(
    "When there is no user for the authentication, we throw NotLoggedInException."
  )
  public void test7() {
    AuthenticationStubs.badAuthenticated(userTestData);
    tester.assertThrows(() -> authenticatedUserService.call())
        .assertException(NotLoggedInException.class);
  }

  @Test
  @DisplayName(
    "When there is no user for the authentication, the exception message is 'This should not happen'"
  )
  public void test8() {
    AuthenticationStubs.badAuthenticated(userTestData);
    tester.assertThrows(() -> authenticatedUserService.call())
        .assertMessageIs(userTestData.SHOULD_NOT_HAPPEN);
  }

}
