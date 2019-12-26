package com.kodekonveyor.market.lead;

import java.util.List;

import com.kodekonveyor.market.register.UserLegalInfoEntityTestData;

public class LeadDTOTestData {

  public final static String INVALID_EMAIL_FORMAT = "gksdl.rew";
  public final static String INVALID_FIRST_NAME_FORMAT = "fesd5@";

  public static final LeadDTO get() {
    final LeadDTO leadDTO = new LeadDTO();
    leadDTO.setEmail(UserLegalInfoEntityTestData.EMAIL);
    leadDTO.setFirstName(LeadEntityTestData.FIRST_NAME);
    leadDTO.setInterest(LeadEntityTestData.INTEREST);
    return leadDTO;
  }

  public static LeadDTO getEmailNull() {
    final LeadDTO dto = get();
    dto.setEmail(null);

    return dto;
  }

  public static LeadDTO getfirstNameInvaliidFormat() {
    final LeadDTO dto = get();
    dto.setFirstName(INVALID_FIRST_NAME_FORMAT);
    return dto;
  }

  public static LeadDTO getInterestNull() {
    final LeadDTO dto = get();
    dto.setInterest(null);
    return dto;
  }

  public static LeadDTO getInvalidEmail() {
    final LeadDTO dto = get();
    dto.setEmail(INVALID_EMAIL_FORMAT);
    return dto;
  }

  public static LeadDTO getNameNull() {
    final LeadDTO dto = get();
    dto.setFirstName(null);
    return dto;
  }

  public static List<LeadDTO> list() {
    return List.of(get());
  }

}
