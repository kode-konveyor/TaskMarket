package com.kodekonveyor.market.lead;

import java.util.List;

import com.kodekonveyor.market.login.RegisterTestData;

public class LeadTestData {

  String INTEREST = "To work with Kodekonveyor";
  String FIRST_NAME = "kumar";
  public final LeadEntity LEAD_ENTITY;
  public long LEAD_ENTITY_ID = 42;
  public LeadDTO LEAD;
  public final RegisterTestData registerTestData;
  public LeadEntity LEAD_ENTITY_NO_ID;
  public List<LeadDTO> LEAD_LIST;
  public List<LeadEntity> LEAD_ENTITY_LIST;
  public final String LIST_LEAD_LOG = "member/lead";
  public String REGISTER_LOG;

  private String createREGISTER_LOG() {
    return "received lead:" + LEAD;
  }

  public LeadTestData(final RegisterTestData registerTestData) {
    this.registerTestData = registerTestData;
    LEAD = createLEAD();
    LEAD_ENTITY_NO_ID = createLEAD_ENTITY_NO_ID();
    LEAD_LIST = List.of(LEAD);
    LEAD_ENTITY = createLEAD_ENTITY();
    LEAD_ENTITY_LIST = List.of(LEAD_ENTITY);
    REGISTER_LOG = createREGISTER_LOG();
  }

  private LeadEntity createLEAD_ENTITY() {
    final LeadEntity entity = createLEAD_ENTITY_NO_ID();
    entity.setId(LEAD_ENTITY_ID);
    return entity;
  }

  private LeadEntity createLEAD_ENTITY_NO_ID() {
    final LeadEntity leadEntity = new LeadEntity();
    leadEntity.setFirstName(FIRST_NAME);
    leadEntity.setEmail(registerTestData.GITHUB_EMAIL);
    leadEntity.setInterest(INTEREST);
    return leadEntity;
  }

  private LeadDTO createLEAD() {
    final LeadDTO leadDTO = new LeadDTO();
    leadDTO.setFirstName(FIRST_NAME);
    leadDTO.setEmail(registerTestData.GITHUB_EMAIL);
    leadDTO.setInterest(INTEREST);
    return leadDTO;
  }

}
