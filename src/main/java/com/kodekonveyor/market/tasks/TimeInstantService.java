package com.kodekonveyor.market.tasks;

import java.time.Instant;

import org.springframework.stereotype.Service;

import com.kodekonveyor.annotations.ExcludeFromCodeCoverage;

@Service
@ExcludeFromCodeCoverage("Utility service")
public class TimeInstantService {

  public Instant call() {
    return Instant.now();
  }

}
