package com.kodekonveyor.market.lead;

import com.kodekonveyor.authentication.ValidationException;

public class EmailIdValidationUtil { //NOPMD

  public static void validateEmail(final LeadDTO lead) {
    if (null == lead.getEmail())
      throw new ValidationException(LeadConstants.EMAIL_NULL_EXCEPTION);

    if (
      !lead.getEmail()
          .matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    )
      throw new ValidationException(
          LeadConstants.INVALID_EMAIL_FORMAT_EXCEPTION
      );
  }
}
