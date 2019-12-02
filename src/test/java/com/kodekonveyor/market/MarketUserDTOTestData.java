package com.kodekonveyor.market;

import com.kodekonveyor.authentication.UserEntityTestData;
import com.kodekonveyor.market.register.MarketUserDTO;
import com.kodekonveyor.market.register.RegistrationInfoDTO;

public class MarketUserDTOTestData {

  public static final MarketUserDTO get() {
    final MarketUserDTO marketUserDTO = new MarketUserDTO();
    marketUserDTO.setLogin(UserEntityTestData.LOGIN);
    marketUserDTO.setRegistrationInfo(new RegistrationInfoDTO());
    return marketUserDTO;
  }

}
