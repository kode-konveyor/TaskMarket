package com.kodekonveyor.market.register;

import lombok.Data;

@Data
public class RegistrationInfoDTO {

  private String country;

  private String email;

  private String legalAddress;

  private String legalName;

  private String paymentDetails;

  private String paymentRegime;

}
