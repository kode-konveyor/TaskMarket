package com.kodekonveyor.market.register;

import lombok.Data;

@Data
public class MarketUserDTO {

  private String login;

  private RegistrationInfoDTO registrationInfo;

}
