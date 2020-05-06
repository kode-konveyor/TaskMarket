package com.kodekonveyor.market.lead;

import java.util.Set;

import javax.annotation.Generated;

@Generated("by zenta-tools")
public class LeadDTOTestData {

  public final static LeadDTO get() {
    final LeadDTO leadDTO = new LeadDTO();
    leadDTO.setId(LeadTestData.ID);
    leadDTO.setFirstName(LeadTestData.FIRST_NAME);
    leadDTO.setEmail(LeadTestData.EMAIL);
    leadDTO.setInterest(LeadTestData.INTEREST);

    return leadDTO;
  };

  public final static Set<LeadDTO> list() {
    final LeadDTO leadDTO = get();
    return Set.of(leadDTO);
  }

  public static LeadDTO getEmailInvalid() {
    final LeadDTO leadDTO = get();
    leadDTO.setEmail(LeadTestData.EMAIL_INVALID);
    return leadDTO;
  }

  public static LeadDTO getEmailNull() {
    final LeadDTO leadDTO = get();
    leadDTO.setEmail(null);
    return leadDTO;
  }

  public static LeadDTO getfirstNameInvalidFormat() {
    final LeadDTO leadDTO = get();
    leadDTO.setFirstName(LeadTestData.FIRST_NAME_INVALID);
    return leadDTO;
  }

  public static LeadDTO getNameNull() {
    final LeadDTO leadDTO = get();
    leadDTO.setFirstName(null);
    return leadDTO;
  }

  public static LeadDTO getInterestNull() {
    final LeadDTO leadDTO = get();
    leadDTO.setInterest(null);
    return leadDTO;
  }

  public static LeadDTO getIdUninitialized() {
    final LeadDTO leadDTO = get();
    leadDTO.setId(null);
    return leadDTO;
  };

}
