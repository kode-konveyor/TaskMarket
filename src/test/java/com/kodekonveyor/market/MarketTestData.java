package com.kodekonveyor.market;

import com.kodekonveyor.authentication.UserTestData;
import com.kodekonveyor.market.register.MarketUserDTO;
import com.kodekonveyor.market.register.RegistrationInfoDTO;

public class MarketTestData {

  public final String LOG_CATEGORY_AUTH_DENY = "auth.deny";
  public MarketUserDTO EMPTY_USER;
  public final String LEAD_DTO_AS_URLENCODED =
      "&email=john.bigboot@example.com&firstName=kumar&interest=To work with Kodekonveyor&";

  private final UserTestData userTestData;

  public MarketTestData(final UserTestData userTestData) {
    this.userTestData = userTestData;
    EMPTY_USER = createEMPTY_USER();
  }

  private MarketUserDTO createEMPTY_USER() {
    final MarketUserDTO marketUserDTO = new MarketUserDTO();
    marketUserDTO.setLogin(userTestData.GITHUB_ID);
    marketUserDTO.setRegistrationInfo(new RegistrationInfoDTO());
    return marketUserDTO;
  }

}
