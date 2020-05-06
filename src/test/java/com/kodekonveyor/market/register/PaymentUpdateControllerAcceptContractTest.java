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
import com.kodekonveyor.authentication.AuthenticatedUserServiceStubs;
import com.kodekonveyor.exception.ThrowableTester;
import com.kodekonveyor.market.UnauthorizedException;
import com.kodekonveyor.market.payment.PaymentDetailsDTOTestData;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("accept contract")
@TestedService("PaymentUpdateController")
public class PaymentUpdateControllerAcceptContractTest
    extends PaymentUpdateControllerTestBase {

  @Test
  @DisplayName("If contract terms are  accepted, no exception is thrown")
  public void test() {
    AuthenticatedUserServiceStubs.registered(authenticatedUserService);
    ThrowableTester.assertNoException(
        () -> paymentUpdateController.call(PaymentDetailsDTOTestData.get())
    );
  }

  @Test
  @DisplayName(
    "If contract terms are not accepted, we throw an UnauthorizedException"
  )
  public void test10() {
    AuthenticatedUserServiceStubs
        .contractTermsNotAccepted(authenticatedUserService);
    ThrowableTester.assertThrows(
        () -> paymentUpdateController.call(PaymentDetailsDTOTestData.get())
    ).assertException(UnauthorizedException.class)
        .assertMessageContains(RegisterTestData.CONTRACT_TERMS_ARE_NOT_ACCEPTED);
  }
}
