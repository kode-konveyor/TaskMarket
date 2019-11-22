package com.kodekonveyor.market.register;

import static org.mockito.Mockito.reset;

class MarketUserCompilerStubs {

  public void behaviour(
      final MarketUserCompilerService marketUserCompilerService,
      final RegisterTestData registerTestData
  ) {
    reset(marketUserCompilerService);

  }
}
