package com.kodekonveyor.market;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kodekonveyor.annotations.ExcludeFromCodeCoverage;

@Service
@ExcludeFromCodeCoverage("interface to underlaying framework")
public class LoggerService {

  private static final Logger logger =
      LoggerFactory.getLogger(LoggerService.class);

  public void call(final String msg) {
    logger.info(msg);
  }

}
