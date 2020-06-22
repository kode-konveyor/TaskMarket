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
import com.kodekonveyor.authentication.AuthenticatedUserServiceStubs;
import com.kodekonveyor.authentication.UserEntityTestData;
import com.kodekonveyor.exception.ThrowableTester;
import com.kodekonveyor.logging.LoggingMarkerConstants;
import com.kodekonveyor.market.payment.PaymentDetailsDTOTestData;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("Logging")
@TestedService("PaymentUpdateController")
public class PaymentUpdateControllerLoggingTest
    extends PaymentUpdateControllerTestBase {

  @Test
  @DisplayName("The call to the service is logged")
  public void testLogging() {
    AuthenticatedUserServiceStubs.registered(authenticatedUserService);
    paymentUpdateController
        .call(PaymentDetailsDTOTestData.get());

    verify(loggerService).info(
        LoggingMarkerConstants.REGISTER,
        PaymentDetailsDTOTestData.get().toString()
    );
  }

  @Test
  @DisplayName(
    "The call to the service is logged when user does not have can_be_paid role while looking for its own data and UnauthorizedException is thrown"
  )
  public void testContractError() {
    AuthenticatedUserServiceStubs
        .contractTermsNotAccepted(authenticatedUserService);
    ThrowableTester.assertThrows(
        () -> paymentUpdateController.call(PaymentDetailsDTOTestData.get())
    );
    verify(loggerService).error(
        LoggingMarkerConstants.REGISTER,
        RegisterTestData.CONTRACT_TERMS_ARE_NOT_ACCEPTED
    );
  }

  @Test
  @DisplayName(
    "The successful completion of call to the service is logged. "
  )
  public void testLoggingAtCompletion() {
    AuthenticatedUserServiceStubs.registered(authenticatedUserService);
    paymentUpdateController
        .call(PaymentDetailsDTOTestData.get());

    verify(loggerService).info(
        LoggingMarkerConstants.REGISTER,
        RegisterTestData.PAYMENT_UPDATED_SUCCESSFULLY
    );
  }

  @Test
  @DisplayName(
    "The call to the service is logged when user does not have can_be_paid role while looking for its own data"
  )
  public void testRoleError() {
    AuthenticatedUserServiceStubs
        .authenticated(authenticatedUserService);
    ThrowableTester.assertThrows(
        () -> paymentUpdateController.call(PaymentDetailsDTOTestData.get())
    );

    verify(loggerService).error(
        LoggingMarkerConstants.REGISTER,
        RegisterTestData.NO_CAN_BE_PAID_ROLE +
            UserEntityTestData.get()
    );
  }
}
