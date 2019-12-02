package com.kodekonveyor.market.register;

public class RegistrationInfoDTOTestData {

  public static final RegistrationInfoDTO get() {
    final RegistrationInfoDTO registrationInfoDTO = new RegistrationInfoDTO();
    registrationInfoDTO.setLegalAddress(UserLegalInfoEntityTestData.ADDRESS);
    registrationInfoDTO.setEmail(UserLegalInfoEntityTestData.EMAIL);
    registrationInfoDTO.setLegalName(UserLegalInfoEntityTestData.LEGALNAME);
    registrationInfoDTO
        .setPaymentRegime(UserLegalInfoEntityTestData.PAYMENT_REGIME);
    registrationInfoDTO
        .setPaymentDetails(UserLegalInfoEntityTestData.PAYMENT_DETAILS);
    registrationInfoDTO.setCountry(UserLegalInfoEntityTestData.COUNTRY);
    return registrationInfoDTO;
  }

}
