package com.kodekonveyor.market.tasks;

import java.time.Instant;
import java.util.Date;

public class DateUtil {

  private static Date date = new Date();
  private static Instant instant = Instant.now();

  public static Date getDate() {
    return date;
  }

  public static Instant getInstant() {
    return instant;
  }

}
