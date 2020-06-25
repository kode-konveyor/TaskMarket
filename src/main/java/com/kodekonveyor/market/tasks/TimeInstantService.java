package com.kodekonveyor.market.tasks;

import java.time.Instant;

import org.springframework.stereotype.Service;

@Service
public class TimeInstantService {

  public Instant call() {
    return Instant.now();
  }

}
