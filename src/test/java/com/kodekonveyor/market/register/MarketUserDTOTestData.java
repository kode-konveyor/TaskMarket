package com.kodekonveyor.market.register;

import com.kodekonveyor.authentication.UserEntityTestData;

public class MarketUserDTOTestData {

  public static final MarketUserDTO get() {
    final MarketUserDTO marketUserDTO = new MarketUserDTO();
    marketUserDTO.setRegistrationInfo(RegistrationInfoDTOTestData.get());
    marketUserDTO.setLogin(UserEntityTestData.LOGIN);
    return marketUserDTO;
  }

  public static final MarketUserDTO getLoginNoMarket() {
    final MarketUserDTO marketUserDTO = new MarketUserDTO();
    marketUserDTO.setRegistrationInfo(new RegistrationInfoDTO());
    marketUserDTO.setLogin(UserEntityTestData.LOGIN_NO_MARKET);
    return marketUserDTO;
  }

}
