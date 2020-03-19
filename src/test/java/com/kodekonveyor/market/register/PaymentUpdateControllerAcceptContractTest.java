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
import com.kodekonveyor.authentication.AuthenticatedUserStubs;
import com.kodekonveyor.exception.ThrowableTester;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("accept contract")
@TestedService("PaymentUpdateController")
public class PaymentUpdateControllerAcceptContractTest
    extends PaymentUpdateControllerTestBase {

  MarketUserDTOTestData registerTestData;

  @Test
  @DisplayName("If contract terms are  accepted, no exception is thrown")
  public void test() {

    AuthenticatedUserStubs.kodekonveyorContract(authenticatedUserService);
    MarketUserStubs
        .contractTermsAccepted(marketUserEntityRepository, registerTestData);
    ThrowableTester.assertNoException(
        () -> paymentUpdateController
            .call(RegisterTestData.PAYMENT_DETAILS)
    );

  }

  @Test
  @DisplayName("If contract terms are not accepted, we throw an exception")
  public void test10() {

    AuthenticatedUserStubs.kodekonveyorContract(authenticatedUserService);
    MarketUserStubs
        .contractTermsNotAccepted(marketUserEntityRepository, registerTestData);
    ThrowableTester.assertThrows(
        () -> paymentUpdateController
            .call(RegisterTestData.PAYMENT_DETAILS)
    )
        .assertMessageIs(
            PaymentUpdateControllerTestData.CONTRACT_TERMS_NOT_ACCEPTED
        );
  }
}
