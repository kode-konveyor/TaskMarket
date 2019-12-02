package com.kodekonveyor.market.register;

import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;

import com.kodekonveyor.authentication.UserEntityTestData;

public class MarketUserStubs {

  public static void
      behaviour(
          final MarketUserEntityRepository marketUserEntityRepository,
          final MarketUserDTOTestData registerTestData
      ) {
    doReturn(List.of(MarketUserEntityTestData.get()))
        .when(marketUserEntityRepository)
        .findByLogin(UserEntityTestData.get());
    doReturn(new ArrayList<MarketUserEntity>()).when(marketUserEntityRepository)
        .findByLogin(UserEntityTestData.getLoginNoMarket());
  }

}
