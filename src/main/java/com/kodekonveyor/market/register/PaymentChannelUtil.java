package com.kodekonveyor.market.register;

import com.kodekonveyor.market.MarketConstants;
import com.kodekonveyor.market.ValidationException;

public class PaymentChannelUtil {

  public static void validatePayment(final String paymentDetails) {
    validatePaypalMode(paymentDetails);
    validateSepaMode(paymentDetails);
    validateTransferwiseMode(paymentDetails);
  }

  public static void validatePaypalMode(final String paymentDetails) {
    final String[] parts = paymentDetails.split(":");
    final String paymentChannel = parts[0];
    final String userPaymentinfo = parts[1];
    final String channelName = "paypal";

    if (
      channelName.equalsIgnoreCase(paymentChannel) &&
          !userPaymentinfo.matches("^(.+)@(.+)$")
    )
      throw new ValidationException(MarketConstants.INVALID_PAYMENT_DETAILS);
  }

  public static void validateSepaMode(final String paymentDetails) {
    final String[] parts = paymentDetails.split(":");

    final String paymentChannel = parts[0];
    final String userPaymentinfo = parts[1];
    final String channelName = "sepa";

    if (
      channelName.equalsIgnoreCase(paymentChannel) &&
          !userPaymentinfo
              .matches("^[a-zA-Z]{4}[a-zA-Z]{2}[a-zA-Z0-9]{2}[XXX0-9]{0,3}")
    )
      throw new ValidationException(MarketConstants.INVALID_PAYMENT_DETAILS);
  }

  public static void validateTransferwiseMode(final String paymentDetails) {
    final String[] parts = paymentDetails.split(":");

    final String paymentChannel = parts[0];
    final String userPaymentinfo = parts[1];
    final String channelName = "transferwise";

    if (
      channelName.equalsIgnoreCase(paymentChannel) &&
          !userPaymentinfo.matches(
              "\\b[A-Z]{2}[0-9]{2}(?:[ ]?[0-9]{4}){4}(?!(?:[ ]?[0-9]){3})(?:[ ]?[0-9]{1,2})?\\b"
          )
    )
      throw new ValidationException(MarketConstants.INVALID_PAYMENT_DETAILS);
  }

}
