package com.kodekonveyor.market.lead;

import java.util.List;

import com.kodekonveyor.market.register.UserLegalInfoEntityTestData;

public class LeadDTOTestData {

  public final static String INVALID_EMAIL_ID = "absxample.com";
  public final static String INVALID_FIRST_NAME = "fss54s6";

  public static final LeadDTO get() {
    final LeadDTO leadDTO = new LeadDTO();
    leadDTO.setEmail(UserLegalInfoEntityTestData.EMAIL);
    leadDTO.setFirstName(LeadEntityTestData.FIRST_NAME);
    leadDTO.setInterest(LeadEntityTestData.INTEREST);
    return leadDTO;
  }

  public static LeadDTO getEmailNull() {
    final LeadDTO leadDTO = get();
    leadDTO.setEmail(null);
    return leadDTO;
  }

  public static LeadDTO getfirstNameInvaliidFormat() {
    final LeadDTO leadDTO = get();
    leadDTO.setFirstName(INVALID_FIRST_NAME);
    return leadDTO;
  }

  public static LeadDTO getInterestNull() {
    final LeadDTO leadDTO = get();
    leadDTO.setInterest(null);
    return leadDTO;
  }

  public static LeadDTO getInvalidEmail() {
    final LeadDTO leadDTO = get();
    leadDTO.setEmail(INVALID_EMAIL_ID);
    return leadDTO;
  }

  public static LeadDTO getNameNull() {
    final LeadDTO leadDTO = get();
    leadDTO.setFirstName(null);
    return leadDTO;
  }

  public static List<LeadDTO> list() {
    return List.of(get());
  }

}
