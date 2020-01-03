package com.kodekonveyor.market.register;

import com.kodekonveyor.market.MarketConstants;
import com.kodekonveyor.market.ValidationException;

public class PaymentChannelUtil { //NOPMD

  public static void paymentChannel(final String paymentDetails) { //NOPMD
    final String[] parts = paymentDetails.split(":");
    final String paymentChannel = parts[0];
    final String userPaymentinfo = parts[1];

    switch (paymentChannel) {
      case "paypal":
        if (
          !userPaymentinfo
              .matches("^(.+)@(.+)$")
        )
          throw new ValidationException(
              MarketConstants.INVALID_PAYMENT_DETAILS
          );
        break;

      case "sepa":
        if (
          !userPaymentinfo
              .matches("^[a-zA-Z]{4}[a-zA-Z]{2}[a-zA-Z0-9]{2}[XXX0-9]{0,3}")
        )

          throw new ValidationException(
              MarketConstants.INVALID_PAYMENT_DETAILS
          );
        break;

      case "tranferwise":
        if (
          !userPaymentinfo.matches(
              "\\b[A-Z]{2}[0-9]{2}(?:[ ]?[0-9]{4}){4}(?!(?:[ ]?[0-9]){3})(?:[ ]?[0-9]{1,2})?\\b"
          )
        )
          throw new ValidationException(
              MarketConstants.INVALID_PAYMENT_DETAILS
          );
        break;

      default:
        break;

    }

  }
}
