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
    marketUserEntity.setLogin(UserEntityTestData.getRoleCanbePayed());
    return marketUserEntity;
  }

  public static final MarketUserEntity getCanBePayed() {
    final MarketUserEntity marketUserEntity = get();
    marketUserEntity.setLogin(UserEntityTestData.getRoleCanbePayed());
    return marketUserEntity;
  }

  public static final MarketUserEntity getCanBePayedRemoved() {
    final MarketUserEntity marketUserEntity = get();
    marketUserEntity.setLegal(null);
    marketUserEntity.setLogin(UserEntityTestData.getCanBePayedRemoved());
    return marketUserEntity;
  }

  public static final MarketUserEntity getNoCanBePayed() {
    final MarketUserEntity marketUserEntity = get();
    marketUserEntity.setLogin(UserEntityTestData.getCanBePayedRemoved());
    return marketUserEntity;
  }

  public static final MarketUserEntity getoutdatedUser() {
    final MarketUserEntity marketUserEntity = get();
    marketUserEntity.setLegal(null);
    marketUserEntity.setLogin(UserEntityTestData.getRoleCanbePayed());
    return marketUserEntity;
  }

  public static final MarketUserEntity getUnacceptedContractuser() {

    final MarketUserEntity marketUserEntity = get();
    marketUserEntity
        .setLegal(UserLegalInfoEntityTestData.getUnacceptedContractUser());
    marketUserEntity.setLogin(UserEntityTestData.getRoleCanbePayed());
    return marketUserEntity;
  }

}
