package com.kodekonveyor.market.register;

import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;

import com.kodekonveyor.authentication.UserTestData;

public class MarketUserStubs {

  public static void
      behaviour(
          final MarketUserEntityRepository marketUserEntityRepository,
          final UserTestData userTestData,
          final RegisterTestData registerTestData
      ) {
    doReturn(List.of(registerTestData.MARKET_USER))
        .when(marketUserEntityRepository)
        .findByLogin(userTestData.TEST_USER_ENTITY);
    doReturn(new ArrayList<MarketUserEntity>()).when(marketUserEntityRepository)
        .findByLogin(userTestData.TEST_USER_ENTITY_NO_MARKET_USER);
  }

}
