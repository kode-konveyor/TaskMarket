package com.kodekonveyor.market.register;

import static org.mockito.Mockito.verify;

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
import com.kodekonveyor.logging.LoggingMarkerConstants;
import com.kodekonveyor.market.MarketTestData;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("Logging")
@TestedService("RegistrationController")
public class RegistrationControllerLoggingTest
    extends RegistrationControllerTestBase {

  @Test
  @DisplayName("The call to the controller is logged")
  public void test() {
    registrationController.call(MarketUserDTOTestData.get());
    verify(logger).info(
        LoggingMarkerConstants.REGISTER,
        MarketUserDTOTestData.get().toString()
    );
  }

  @Test
  @DisplayName("The return of DTO logged")
  public void test2() {
    registrationController.call(MarketUserDTOTestData.get());
    verify(logger).debug(
        LoggingMarkerConstants.REGISTER,
        MarketTestData.MARKET_USER_RETURNED_SUCCESSFULLY +
            MarketUserDTOTestData.get().getId()
    );
  }

}
