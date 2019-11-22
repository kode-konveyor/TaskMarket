package com.kodekonveyor.market.lead;

import java.util.List;
import java.util.Set;

import com.kodekonveyor.market.register.RegisterTestData;

public class LeadTestData {

  public final LeadEntity LEAD_ENTITY;

  public final String FIRST_NAME = "kumar";

  public final String INTEREST = "To work with Kodekonveyor";

  public final LeadDTO LEAD;

  public final Set<LeadEntity> LEAD_ENTITY_LIST;

  public final LeadEntity LEAD_ENTITY_NO_ID;

  private final RegisterTestData registerTestData;

  public final long LEAD_ID = 0x134d1d;

  public final Set<LeadEntity> LEAD_LIST;

  public final String LIST_LEAD_LOG = "/member/lead";

  public final String REGISTER_LOG =
      "received lead:LeadDTO(firstName=kumar, email=john.bigboot@example.com, interest=To work with Kodekonveyor)";

  public final List<LeadDTO> LEAD_DTO_LIST;

  public LeadTestData(final RegisterTestData registerTestData) {
    this.registerTestData = registerTestData;
    LEAD_ENTITY = createLEAD_ENTITY();
    LEAD_LIST = Set.of(LEAD_ENTITY);
    LEAD_ENTITY_NO_ID = createLEAD_ENTITY_NO_ID();
    LEAD_ENTITY_LIST = Set.of(LEAD_ENTITY);
    LEAD = createLEAD();
    LEAD_DTO_LIST = List.of(LEAD);
  }

  private LeadDTO createLEAD() {
    final LeadDTO leadDTO = new LeadDTO();
    leadDTO.setEmail(registerTestData.EMAIL);
    leadDTO.setFirstName(FIRST_NAME);
    leadDTO.setInterest(INTEREST);
    return leadDTO;
  }

  private LeadEntity createLEAD_ENTITY() {
    final LeadEntity leadEntity = createLEAD_ENTITY_NO_ID();
    leadEntity.setId(LEAD_ID);
    return leadEntity;
  }

  private LeadEntity createLEAD_ENTITY_NO_ID() {
    final LeadEntity leadEntity = new LeadEntity();
    leadEntity.setEmail(registerTestData.EMAIL);
    leadEntity.setFirstName(FIRST_NAME);
    leadEntity.setInterest(INTEREST);
    return leadEntity;
  }
}
