package com.kodekonveyor.market.tasks;

import java.time.Instant;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class DateUtilService {

  private static Date date = new Date();
  private static Instant instant = Instant.now();

  public Date getDate() {
    return date;
  }

  public Instant getInstant() {
    return instant;
  }

}
