package com.kodekonveyor.market.register;

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
@TestedBehaviour("check regime validity")
@TestedService("RegistrationController")
public class RegistrationControllerCheckRegimeValidityTest
    extends RegistrationControllerTestBase {

  @Test
  @DisplayName("When the payment regime is invalid, we throw an exception")
  public void emailFormattest() {
    ThrowableTester.assertThrows(
        () -> registrationController
            .call(RegistrationInfoDTOTestData.getInvalidRegime())
    )

        .assertMessageIs(
            RegistrationInfoDTOTestData.INVALID_PAYMENT_REGIME_EXCEPTION
        );

  }
}
