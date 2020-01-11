package com.kodekonveyor.market.register;

import com.kodekonveyor.market.MarketConstants;
import com.kodekonveyor.market.ValidationException;

public class PaymentChannelUtil {

  private final static String paypalRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
  private final static String sepaRegex =
      "^[a-zA-Z]{4}[a-zA-Z]{2}[a-zA-Z0-9]{2}[XXX0-9]{0,3}";
  private final static String seperator = ":";
  private final static String transferwiseRegex =
      "\\b[A-Z]{2}[0-9]{2}(?:[ ]?[0-9]{4}){4}(?!(?:[ ]?[0-9]){3})(?:[ ]?[0-9]{1,2})?\\b";

  public static void validatePaymentDetails(final String paymentDetails) { //NOPMD

    final String[] parts = paymentDetails.split(seperator);
    final String paymentChannel = parts[0];
    final String userPaymentinfo = parts[1];

    if (
      MarketConstants.PAYPAL.equalsIgnoreCase(paymentChannel) &&
          !userPaymentinfo.matches(paypalRegex)
    )
      throw new ValidationException(MarketConstants.INVALID_PAYMENT_DETAILS);

    if (
      MarketConstants.SEPA.equalsIgnoreCase(paymentChannel) &&
          !userPaymentinfo
              .matches(sepaRegex)
    )
      throw new ValidationException(MarketConstants.INVALID_PAYMENT_DETAILS);

    if (
      MarketConstants.TRANSFERWISE.equalsIgnoreCase(paymentChannel) &&
          !userPaymentinfo
              .matches(transferwiseRegex)
    )
      throw new ValidationException(MarketConstants.INVALID_PAYMENT_DETAILS);
  }
}
