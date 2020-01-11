package com.kodekonveyor.market.register;

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
import com.kodekonveyor.authentication.AuthenticatedUserStubs;
import com.kodekonveyor.exception.ThrowableTester;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("payment channels")
@TestedService("PaymentUpdateController")
public class PaymentUpdateControllerPaymentChannelsTest
    extends PaymentUpdateControllerTestBase {

  MarketUserDTOTestData registerTestData;

  @BeforeEach
  void setUp() {
    MarketUserStubs
        .behaviour(marketUserEntityRepository, registerTestData);
  }

  @Test
  @DisplayName("For chosen payment channel, right information is supplied")
  public void test() {
    AuthenticatedUserStubs
        .authenticated(authenticatedUserService);
    assertEquals(
        MarketUserDTOTestData.get(),
        paymentUpdateController
            .call(PaymentUpdateControllerTestData.PAYMENT_DETAILS)
    );
  }

  @Test
  @DisplayName("If payment details are incorrect, we throw exception")
  public void test1() {
    AuthenticatedUserStubs
        .authenticated(authenticatedUserService);
    final ThrowableTester tester = new ThrowableTester();
    tester
        .assertThrows(
            () -> paymentUpdateController
                .call(PaymentUpdateControllerTestData.INVALID_PAYMENT_DETAILS)
        )
        .assertMessageIs(
            PaymentUpdateControllerTestData.INVALID_PAYMENT_DETAILS_EXCEPTION
        );
  }
}
