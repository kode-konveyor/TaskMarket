package com.kodekonveyor.market.lead;

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
import com.kodekonveyor.exception.ThrowableTester;
import com.kodekonveyor.logging.LoggingMarkerConstants;
import com.kodekonveyor.market.MarketTestData;
import com.kodekonveyor.market.RegisterInterestControllerTestData;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("Logging")
@TestedService("RegisterInterestController")
public class RegisterInterestControllerLoggingTest
    extends RegisterInterestControllerTestBase {

  @Test
  @DisplayName("The call of the service is logged with the created entity")
  public void testLogging() {
    registerInterestController.call(LeadDTOTestData.get());
    verify(loggerService)
        .info(
            LoggingMarkerConstants.LEAD,
            RegisterInterestControllerTestData.CALL +
                LeadDTOTestData.get().toString()
        );
  }

  @Test
  @DisplayName(
    "The call of the service is logged for the successfully saved entity"
  )
  public void testLoggingAfterCompletion() {
    registerInterestController.call(LeadDTOTestData.get());
    verify(loggerService)
        .info(
            LoggingMarkerConstants.LEAD,
            RegisterInterestControllerTestData.SAVED +
                LeadDTOTestData.get().toString()
        );
  }

  @Test
  @DisplayName(
    "The call of the service is logged when error of null Interest"
  )
  void testLoggingError() {
    ThrowableTester.assertThrows(
        () -> registerInterestController.call(LeadDTOTestData.getInterestNull())
    );
    verify(loggerService)
        .error(
            LoggingMarkerConstants.LEAD,
            MarketTestData.INTEREST_NULL_EXCEPTION +
                LeadDTOTestData.getInterestNull().toString()
        );
  }

  @Test
  @DisplayName(
    "The call of service is logged when using application/x-www-form-urlencoded request"
  )
  public void testUrlEncodedLogging() {
    registerInterestController.callForUrlencoded(LeadDTOTestData.get());
    verify(loggerService)
        .info(
            LoggingMarkerConstants.LEAD,
            RegisterInterestControllerTestData.CALL +
                LeadDTOTestData.get().toString()
        );
  }
}
