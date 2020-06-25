package com.kodekonveyor.market.tasks;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class DateUtilTestData {

  public static final Date DATE = stringToDate("2020-06-24 10:00:00");

  public static final Instant INSTANT =
      Instant.parse("2020-06-24T10:00:00.00Z");

  //Instant.now(Clock.systemDefaultZone());
  public static Date stringToDate(final String s) {

    Date result = null;
    try {
      final SimpleDateFormat dateFormat =
          new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      result = dateFormat.parse(s);
    } catch (final ParseException e) {
      e.printStackTrace();
    }
    return result;
  }
}
