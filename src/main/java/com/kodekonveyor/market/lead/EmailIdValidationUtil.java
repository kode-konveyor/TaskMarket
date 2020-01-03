package com.kodekonveyor.market.lead;

import com.kodekonveyor.authentication.ValidationException;
import com.kodekonveyor.market.MarketConstants;

public class EmailIdValidationUtil { //NOPMD

  public static void validateEmail(final LeadDTO lead) {
    if (null == lead.getEmail())
      throw new ValidationException(MarketConstants.EMAIL_NULL_EXCEPTION);

    if (
      !lead.getEmail()
          .matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    )
      throw new ValidationException(
          MarketConstants.INVALID_EMAIL_FORMAT_EXCEPTION
      );
  }
}
