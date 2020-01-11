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
@TestedBehaviour("payment channels")
@TestedService("PaymentUpdateController")
public class PaymentUpdateControllerPaymentChannelsTest
    extends PaymentUpdateControllerTestBase {

  MarketUserDTOTestData registerTestData;

  @Test
  @DisplayName("If payment details are incorrect, we throw exception")
  public void test1() {

    ThrowableTester.assertThrows(
        () -> paymentUpdateController
            .call(RegisterTestData.INVALID_PAYMENT_DETAILS)
    )
        .assertMessageIs(
            PaymentUpdateControllerTestData.INVALID_PAYMENT_DETAILS_EXCEPTION
        );
  }
}
