package com.kodekonveyor.market.kpi;

import static org.mockito.Mockito.doReturn;

import java.util.Optional;

public class EventEntityRepositoryStubs {

  public static void
      behaviour(final EventEntityRepository eventEntityRepository) {

    doReturn(Optional.of(EventEntityTestData.getIdZero()))
        .when(eventEntityRepository)
        .findById(EventTestData.ID_0);

  }

}
