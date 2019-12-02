package com.kodekonveyor.market.lead;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.reset;

public class LeadEntityStubs {

  public static void behaviour(
      final LeadEntityRepository leadEntityRepository
  ) {
    reset(leadEntityRepository);
    doReturn(LeadEntityTestData.list()).when(leadEntityRepository)
        .findAll();
  }

}
