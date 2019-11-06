package com.kodekonveyor.market.lead;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodekonveyor.market.LoggerService;

@RestController
public class RegisterInterestController {

  @Autowired
  LeadEntityRepository leadEntityRepository;

  @Autowired
  LoggerService loggerService;

  @PostMapping("/lead")
  public LeadDTO call(final LeadDTO lead) {
    loggerService.call("received lead:" + lead);
    final LeadEntity leadEntity = new LeadEntity();
    leadEntity.setEmail(lead.getEmail());
    leadEntity.setFirstName(lead.getFirstName());
    leadEntity.setInterest(lead.getInterest());
    leadEntityRepository.save(leadEntity);
    return lead;
  }

}
