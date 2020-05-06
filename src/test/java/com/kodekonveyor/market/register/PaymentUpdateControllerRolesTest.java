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
@TestedBehaviour("roles")
@TestedService("PaymentUpdateController")
public class PaymentUpdateControllerRolesTest
    extends PaymentUpdateControllerTestBase {

  @Test
  @DisplayName(
    "if the user does not have can_be_paid role while looking for its own data, an UnauthorizedException is thrown"
  )
  void test() {
    AuthenticatedUserServiceStubs.authenticated(authenticatedUserService);
    ThrowableTester.assertThrows(
        () -> paymentUpdateController.call(PaymentDetailsDTOTestData.get())

    ).assertException(UnauthorizedException.class);

  }

  @Test
  @DisplayName(
    "if the user does not have can_be_paid role while looking for its own data, the message contains 'no can be paid role for user'"
  )
  void test11() {
    AuthenticatedUserServiceStubs.authenticated(authenticatedUserService);
    ThrowableTester.assertThrows(
        () -> paymentUpdateController.call(PaymentDetailsDTOTestData.get())

    ).assertMessageContains(
        RegisterConstants.NO_CAN_BE_PAID_ROLE
    );

  }

  @Test
  @DisplayName(
    "if the user have can_be_paid role, no Exception is thrown"
  )
  void test2() {
    AuthenticatedUserServiceStubs.registered(authenticatedUserService);
    ThrowableTester.assertNoException(
        () -> paymentUpdateController.call(PaymentDetailsDTOTestData.get())

    );

  }
}
