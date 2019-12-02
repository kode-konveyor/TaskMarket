package com.kodekonveyor.market.lead;

import java.util.List;

import com.kodekonveyor.market.register.UserLegalInfoEntityTestData;

public class LeadDTOTestData {

  public static final LeadDTO get() {
    final LeadDTO leadDTO = new LeadDTO();
    leadDTO.setEmail(UserLegalInfoEntityTestData.EMAIL);
    leadDTO.setFirstName(LeadEntityTestData.FIRST_NAME);
    leadDTO.setInterest(LeadEntityTestData.INTEREST);
    return leadDTO;
  }

  public static List<LeadDTO> list() {
    return List.of(get());
  }

}
