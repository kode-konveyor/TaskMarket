package com.kodekonveyor.market.register;

import com.kodekonveyor.market.MarketConstants;
import com.kodekonveyor.market.ValidationException;

public class PaymentChannelUtil {

  private static final String[][] checks = {

      {
          MarketConstants.CHANNEL_NAME_PAYPAL, MarketConstants.REGEX_PAYPAL
      },
      {
          MarketConstants.CHANNEL_NAME_SEPA, MarketConstants.REGEX_SEPA
      },

      {
          MarketConstants.CHANNEL_NAME_TRANSFERWISE,
          MarketConstants.REGEX_TRANSFERWISE
      }
  };

  public static void validatePaymentDetails(final String paymentDetails) {
    final String[] parts =
        paymentDetails.split(MarketConstants.PAYMMENT_CHANNEL_SEPARATOR);
    final String paymentChannel = parts[0];
    final String userPaymentinfo = parts[1];

    for (final String[] check : checks) {

      final String channelName = check[0];
      final String regex = check[1];

      if (
        channelName.equals(paymentChannel) &&
            userPaymentinfo.matches(regex)
      )
        return;

    }
    throw new ValidationException(MarketConstants.INVALID_PAYMENT_DETAILS);

  }
}
