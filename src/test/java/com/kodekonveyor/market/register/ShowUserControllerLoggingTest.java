package com.kodekonveyor.market.register;

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
    showUserController.call();
    Mockito.verify(logger).info(
        LoggingMarkerConstants.REGISTER, UserEntityTestData.get().toString()
    );
  }

  @Test
  @DisplayName("The return of DTO is logged")
  public void test1() {
    AuthenticatedUserServiceStubs
        .authenticated(authenticatedUserService);
    showUserController.call();
    Mockito.verify(logger).debug(
        LoggingMarkerConstants.REGISTER,
        MarketTestData.MARKET_USER_RETURNED_SUCCESSFULLY +
            MarketUserDTOTestData.get().getId()
    );

  }

}
