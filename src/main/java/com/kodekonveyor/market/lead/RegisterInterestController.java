package com.kodekonveyor.market.lead;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kodekonveyor.market.LogSeverityEnum;
import com.kodekonveyor.market.LoggerService;
import com.kodekonveyor.market.UrlMapConstants;

@RestController
public class RegisterInterestController {

  @Autowired
  LeadEntityRepository leadEntityRepository;

  @Autowired
  LoggerService loggerService;

  @PostMapping(value = UrlMapConstants.LEAD_PATH, consumes = "application/json")
  public LeadDTO call(final @RequestBody LeadDTO lead) {
    doStore(lead);
    return lead;
  }

  @PostMapping(
      value = UrlMapConstants.LEAD_PATH,
      consumes = "application/x-www-form-urlencoded"
  )
  public LeadDTO callForUrlencoded(final LeadDTO lead) {
    doStore(lead);
    return lead;
  }

  private void doStore(final LeadDTO lead) {
    loggerService.call("lead.received", LogSeverityEnum.INFO, lead.toString());
    final LeadEntity leadEntity = new LeadEntity();
    leadEntity.setEmail(lead.getEmail());
    leadEntity.setFirstName(lead.getFirstName());
    leadEntity.setInterest(lead.getInterest());
    leadEntityRepository.save(leadEntity);
  }

}
