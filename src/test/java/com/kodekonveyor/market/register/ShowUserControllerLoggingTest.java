package com.kodekonveyor.market.register;

import com.kodekonveyor.exception.ThrowableTester;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.kodekonveyor.annotations.TestedBehaviour;
import com.kodekonveyor.annotations.TestedService;
import com.kodekonveyor.authentication.AuthenticatedUserServiceStubs;
import com.kodekonveyor.authentication.UserEntityTestData;
import com.kodekonveyor.logging.LoggingMarkerConstants;
import com.kodekonveyor.market.MarketTestData;

import static com.kodekonveyor.authentication.UserTestData.LOGIN_BAD;
import static com.kodekonveyor.market.register.RegisterTestData.EXP_LOG_SHOW_MARKET_USER_FAILURE;
import static com.kodekonveyor.market.register.RegisterTestData.EXP_USER_NOT_FOUND;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("Logging")
@TestedService("ShowUserController")
public class ShowUserControllerLoggingTest extends ShowUserControllerTestBase {

  @Test
  @DisplayName("The call to the controller is logged")
  public void test() {
    AuthenticatedUserServiceStubs
        .authenticated(authenticatedUserService);
    showUserController.call(null);
    Mockito.verify(logger).info(
        LoggingMarkerConstants.REGISTER, UserEntityTestData.get().toString()
    );
  }

  @Test
  @DisplayName("The return of DTO is logged")
  public void test1() {
    AuthenticatedUserServiceStubs
        .authenticated(authenticatedUserService);
    showUserController.call(null);
    Mockito.verify(logger).debug(
        LoggingMarkerConstants.REGISTER,
        MarketTestData.MARKET_USER_RETURNED_SUCCESSFULLY +
            MarketUserDTOTestData.get().getId()
    );

  }

  @Test
  @DisplayName("Error message is logged, when input user is not present")
  public void test3() {
    AuthenticatedUserServiceStubs
            .authenticated(authenticatedUserService);
    ThrowableTester.assertThrows(() -> showUserController.call(LOGIN_BAD));

    Mockito.verify(logger).warn(
            LoggingMarkerConstants.REGISTER,
            EXP_LOG_SHOW_MARKET_USER_FAILURE,
            LOGIN_BAD,
            EXP_USER_NOT_FOUND
    );
  }

}
