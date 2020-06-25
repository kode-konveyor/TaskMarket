package com.kodekonveyor.market.kpi;

import static org.mockito.Mockito.doReturn;

import com.kodekonveyor.market.tasks.DateUtilService;
import com.kodekonveyor.market.tasks.DateUtilTestData;

public class DateUtilStubs {

  public static void
      getDate(final DateUtilService dateUtilService) {
    doReturn(DateUtilTestData.DATE).when(dateUtilService).getDate();
  }

  public static void
      getInstant(final DateUtilService dateUtilService) {
    doReturn(DateUtilTestData.INSTANT).when(dateUtilService).getInstant();
  }
}
