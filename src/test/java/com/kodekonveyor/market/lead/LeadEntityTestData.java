package com.kodekonveyor.market.lead;

import java.util.Set;

import javax.annotation.Generated;

@Generated("by zenta-tools")
public class LeadEntityTestData {

  public final static LeadEntity get() {
    final LeadEntity leadEntity = new LeadEntity();
    leadEntity.setId(LeadTestData.ID);
    leadEntity.setFirstName(LeadTestData.FIRST_NAME);
    leadEntity.setEmail(LeadTestData.EMAIL);
    leadEntity.setInterest(LeadTestData.INTEREST);

    return leadEntity;
  };

  public final static Set<LeadEntity> list() {
    return Set.of(get());
  }

  public static LeadEntity getIdUninitialized() {
    final LeadEntity leadEntity = get();
    leadEntity.setId(null);
    return leadEntity;
  };

}
