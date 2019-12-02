package com.kodekonveyor.authentication;

import static org.mockito.Mockito.verify;

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
import com.kodekonveyor.market.LogSeverityEnum;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("Data access")
@TestedService("AuthenticatedUserService")
public class AuthenticatedUserServiceLoggingTest
    extends AuthenticatedUserServiceTestBase {

  @BeforeEach
  @Override
  public void setUp() {
    super.setUp();
    AuthenticationStubs.authenticated();
    authenticatedUserService.call();
  }

  @Test
  @DisplayName("Logs the username based on external authentication")
  public void test() {
    verify(loggerService)
        .call(
            RemoteAuthenticationFilterTestData.LOGIN, LogSeverityEnum.INFO,
            UserEntityTestData.LOGIN
        );
  }

}
