package com.kodekonveyor.market.lead;

import com.kodekonveyor.market.MarketConstants;
import com.kodekonveyor.market.ValidationException;

public class FirstNameValidationUtil {

  public static void validateFirstName(final LeadDTO lead) {
    if (null == lead.getFirstName())

      throw new ValidationException(MarketConstants.FIRST_NAME_NULL_EXCEPTION);

    if (!lead.getFirstName().matches("[A-Za-z]*"))
      throw new ValidationException(

          MarketConstants.INVALID_FIRST_NAME_FORMAT_EXCEPTION
      );
  }
}
