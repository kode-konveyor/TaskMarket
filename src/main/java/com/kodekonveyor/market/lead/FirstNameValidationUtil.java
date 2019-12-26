package com.kodekonveyor.market.lead;

import com.kodekonveyor.authentication.ValidationException;

public class FirstNameValidationUtil { //NOPMD

  public static void validateFirstName(final LeadDTO lead) {
    if (null == lead.getFirstName())
      throw new ValidationException(LeadConstants.FIRST_NAME_NULL_EXCEPTION);

    if (!lead.getFirstName().matches("[A-Za-z]*"))
      throw new ValidationException(
          LeadConstants.INVALID_FIRST_NAME_FORMAT_EXCEPTION
      );
  }
}
