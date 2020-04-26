package com.kodekonveyor.market.lead;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kodekonveyor.logging.LoggingMarkerConstants;
import com.kodekonveyor.market.MarketConstants;
import com.kodekonveyor.market.UrlMapConstants;
import com.kodekonveyor.market.ValidationException;

@RestController
public class RegisterInterestController {

  @Autowired
  LeadEntityRepository leadEntityRepository;

  @Autowired
  Logger loggerService;

  @PostMapping(value = UrlMapConstants.LEAD_PATH, consumes = "application/json")
  public LeadDTO call(final @RequestBody LeadDTO lead) {
    doStore(lead);

    EmailIdValidationUtil.validateEmail(lead);
    FirstNameValidationUtil.validateFirstName(lead);
    validateInterest(lead);

    return lead;
  }

  @PostMapping(
      value = UrlMapConstants.LEAD_PATH,
      consumes = "application/x-www-form-urlencoded"
  )
  public LeadDTO callForUrlencoded(final LeadDTO lead) {
    return call(lead);

  }

  private void doStore(final LeadDTO lead) {
    loggerService.info(
        LoggingMarkerConstants.LEAD, LeadConstants.STORE_LEAD + lead.toString()
    );
    final LeadEntity leadEntity = new LeadEntity();
    leadEntity.setEmail(lead.getEmail());
    leadEntity.setFirstName(lead.getFirstName());
    leadEntity.setInterest(lead.getInterest());
    leadEntityRepository.save(leadEntity);
  }

  private void validateInterest(final LeadDTO lead) {
    if (null == lead.getInterest())

      throw new ValidationException(MarketConstants.INTEREST_NULL_EXCEPTION);

  }

}
