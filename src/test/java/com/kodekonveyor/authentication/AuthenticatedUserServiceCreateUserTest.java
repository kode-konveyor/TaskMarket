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

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("Data access")
@TestedService("AuthenticatedUserService")
public class AuthenticatedUserServiceCreateUserTest
    extends AuthenticatedUserServiceTestBase {

  @Test
  @DisplayName("When there is no user for the credential, we create it")
  public void test6() {
    AuthenticationStubs.badAuthenticated(userTestData);
    assertEquals(userTestData.BAD_USER, authenticatedUserService.call());
  }

}
