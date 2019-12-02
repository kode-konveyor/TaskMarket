package com.kodekonveyor.market.lead;

import java.util.List;

import com.kodekonveyor.market.register.UserLegalInfoEntityTestData;

public class LeadEntityTestData {

  public static final String FIRST_NAME = "kumar";
  public static final String INTEREST = "To work with Kodekonveyor";
  public static final long LEAD_ID = 0x134d1d;

  public static final LeadEntity get() {
    final LeadEntity leadEntity = getIdUninitialized();
    leadEntity.setId(LEAD_ID);
    return leadEntity;
  }

  public static final LeadEntity getIdUninitialized() {
    final LeadEntity leadEntity = new LeadEntity();
    leadEntity.setEmail(UserLegalInfoEntityTestData.EMAIL);
    leadEntity.setFirstName(FIRST_NAME);
    leadEntity.setInterest(INTEREST);
    return leadEntity;
  }

  public static final List<LeadEntity> list() {
    return List.of(get());
  }
}
