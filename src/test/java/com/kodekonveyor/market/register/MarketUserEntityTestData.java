package com.kodekonveyor.market.register;

import com.kodekonveyor.authentication.UserEntityTestData;

public class MarketUserEntityTestData {

  public static final MarketUserEntity get() {

    final MarketUserEntity marketUserEntity = new MarketUserEntity();
    marketUserEntity.setLegal(UserLegalInfoEntityTestData.get());
    marketUserEntity.setLogin(UserEntityTestData.get());
    return marketUserEntity;
  }

  public static Object getAcceptedContractuser() {
    final MarketUserEntity marketUserEntity = get();
    marketUserEntity
        .setLegal(UserLegalInfoEntityTestData.getAcceptedContractUser());
    marketUserEntity.setLogin(UserEntityTestData.getRoleKodekonveyorContract());
    return marketUserEntity;
  }

  public static final MarketUserEntity getUnacceptedContractuser() {

    final MarketUserEntity marketUserEntity = get();
    marketUserEntity
        .setLegal(UserLegalInfoEntityTestData.getUnacceptedContractUser());
    marketUserEntity.setLogin(UserEntityTestData.getRoleKodekonveyorContract());
    return marketUserEntity;
  }
}
