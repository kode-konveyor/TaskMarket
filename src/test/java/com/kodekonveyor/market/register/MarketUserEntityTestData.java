package com.kodekonveyor.market.register;

import com.kodekonveyor.authentication.UserEntityTestData;

public class MarketUserEntityTestData {

  public static final MarketUserEntity get() {

    final MarketUserEntity marketUserEntity = new MarketUserEntity();
    marketUserEntity.setLegal(UserLegalInfoEntityTestData.get());
    marketUserEntity.setLogin(UserEntityTestData.get());
    return marketUserEntity;
  }

}
