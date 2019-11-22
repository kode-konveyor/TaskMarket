package com.kodekonveyor.market.lead;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.reset;

public class LeadEntityStubs {

  public static void behaviour(
      final LeadEntityRepository leadEntityRepository,
      final LeadTestData leadTestData
  ) {
    reset(leadEntityRepository);
    doReturn(leadTestData.LEAD_ENTITY_LIST).when(leadEntityRepository)
        .findAll();
  }

}
