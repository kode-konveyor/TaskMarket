package com.kodekonveyor.market.lead;

import static org.mockito.Mockito.doReturn;

public class LeadEntityStubs {

  public static void behaviour(
      final LeadEntityRepository leadEntityRepository,
      final LeadTestData leadTestData
  ) {
    doReturn(leadTestData.LEAD_ENTITY_LIST).when(leadEntityRepository)
        .findAll();
  }

}
