package com.kodekonveyor.market.kpi;

import static org.mockito.Mockito.doReturn;

import com.kodekonveyor.market.tasks.TimeInstantService;
import com.kodekonveyor.market.tasks.TimeInstantTestData;

public class TimeInstantServiceStubs {

  public static void
      behaviour(final TimeInstantService timeInstantService) {
    doReturn(TimeInstantTestData.INSTANT).when(timeInstantService).call();
  }

}
