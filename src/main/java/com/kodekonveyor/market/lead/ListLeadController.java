package com.kodekonveyor.market.lead;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodekonveyor.market.LoggerService;

@RestController
public class ListLeadController {

  @Autowired
  LeadEntityRepository leadEntityRepository;

  @Autowired
  LoggerService loggerService;

  @GetMapping("/member/lead")
  public List<LeadDTO> call() {
    loggerService.call("member/lead");
    final Iterable<LeadEntity> leads = leadEntityRepository.findAll();
    final List<LeadDTO> ret = new ArrayList<>();
    for (final LeadEntity lead : leads) {
      final LeadDTO leadDTO = new LeadDTO();//NOPMD
      leadDTO.setEmail(lead.getEmail());
      leadDTO.setFirstName(lead.getFirstName());
      leadDTO.setInterest(lead.getInterest());
      ret.add(leadDTO);
    }
    return ret;
  }
}
