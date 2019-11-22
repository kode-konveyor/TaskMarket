package com.kodekonveyor.market;

import static org.mockito.Mockito.reset;

class LoggerStubs {

  public void behaviour(final LoggerService loggerService) {
    reset(loggerService);
  }
}
