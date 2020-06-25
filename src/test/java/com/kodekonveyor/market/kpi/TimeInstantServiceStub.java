package com.kodekonveyor.market.kpi;

import static org.mockito.Mockito.doReturn;

import com.kodekonveyor.market.tasks.TimeInstantTestData;
import com.kodekonveyor.market.tasks.TimeInstantService;

public class TimeInstantServiceStub {

  public static void
      getInstant(final TimeInstantService timeInstantService) {
    doReturn(TimeInstantTestData.INSTANT).when(timeInstantService).call();
  }

}
