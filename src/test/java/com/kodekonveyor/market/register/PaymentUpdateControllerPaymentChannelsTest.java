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
import com.kodekonveyor.market.ValidationException;
import com.kodekonveyor.market.payment.PaymentDetailsDTOTestData;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("payment channels")
@TestedService("PaymentUpdateController")
public class PaymentUpdateControllerPaymentChannelsTest
    extends PaymentUpdateControllerTestBase {

  @Test
  @DisplayName(
    "If there is one channel with less than 100%, a ValidationException is thrown"
  )
  public void test1() {
    AuthenticatedUserServiceStubs.registered(authenticatedUserService);
    ThrowableTester.assertThrows(
        () -> paymentUpdateController
            .call(PaymentDetailsDTOTestData.getSumLessThanHundredPercent())
    ).assertException(ValidationException.class);
  }

  @Test
  @DisplayName(
    "If there is one channel with less than 100%, a ValidationException is thrown"
  )
  public void test2() {
    AuthenticatedUserServiceStubs.registered(authenticatedUserService);
    ThrowableTester.assertThrows(
        () -> paymentUpdateController
            .call(PaymentDetailsDTOTestData.getSumLessThanHundredPercent())
    ).assertMessageContains(RegisterTestData.THE_SUM_OF_PAYMENTS_IS_NOT_100);
  }

  @Test
  @DisplayName(
    "If there are more channels with sum of 100%, it is accepted"
  )
  public void test3() {
    AuthenticatedUserServiceStubs.registered(authenticatedUserService);
    ThrowableTester.assertNoException(
        () -> paymentUpdateController
            .call(PaymentDetailsDTOTestData.getSumHundredPercentInTwoChannels())
    );
  }

  @Test
  @DisplayName(
    "If there are more channels with sum > than 100%, a ValidationException is thrown"
  )
  public void test4() {
    AuthenticatedUserServiceStubs.registered(authenticatedUserService);
    ThrowableTester.assertThrows(
        () -> paymentUpdateController
            .call(
                PaymentDetailsDTOTestData
                    .getSumMoreThanHundredPercentInTwoChannels()
            )
    ).assertException(ValidationException.class);
  }

  @Test
  @DisplayName(
    "If there are more channels with sum < than 100%, a ValidationException is thrown"
  )
  public void test5() {
    AuthenticatedUserServiceStubs.registered(authenticatedUserService);
    ThrowableTester.assertThrows(
        () -> paymentUpdateController
            .call(
                PaymentDetailsDTOTestData
                    .getSumLessThanHundredPercentInTwoChannels()
            )
    ).assertException(ValidationException.class);
  }

  @Test
  @DisplayName(
    "If there is a channel with invalid payment channel, a ValidationException is thrown"
  )
  public void test6() {
    AuthenticatedUserServiceStubs.registered(authenticatedUserService);
    ThrowableTester.assertThrows(
        () -> paymentUpdateController
            .call(
                PaymentDetailsDTOTestData
                    .getWithInvalidChannel()
            )
    ).assertException(ValidationException.class);
  }

  @Test
  @DisplayName(
    "If there is a channel with invalid payment channel, the error message contains 'invalid transfer type'"
  )
  public void test7() {
    AuthenticatedUserServiceStubs.registered(authenticatedUserService);
    ThrowableTester.assertThrows(
        () -> paymentUpdateController
            .call(
                PaymentDetailsDTOTestData
                    .getWithInvalidChannel()
            )
    ).assertMessageContains(RegisterTestData.INVALID_TRANSFER_TYPE);
  }

}
