package com.kodekonveyor.market.register;

public class UserLegalInfoEntityTestData {

  public static final String ADDRESS =
      "Lectroid str. 25, 12345, Shelby, Alabama, US";

  public static final String COUNTRY = "es";

  public static final String EMAIL = "john.bigboot@example.com";

  public static final String LEGALNAME = "John Bigbooté";

  public static final String PAYMENT_DETAILS =
      "paypal:john.bigboot@example.com";
  public static final String PAYMENT_REGIME = "restofworld";

  public static final UserLegalInfoEntity get() {
    final UserLegalInfoEntity userLegalInfoEntity = new UserLegalInfoEntity();
    userLegalInfoEntity.setLegalAddress(ADDRESS);
    userLegalInfoEntity.setEmail(EMAIL);
    userLegalInfoEntity.setLegalName(LEGALNAME);
    userLegalInfoEntity.setPaymentRegime(PAYMENT_REGIME);
    userLegalInfoEntity.setPaymentDetails(PAYMENT_DETAILS);
    userLegalInfoEntity.setCountry(COUNTRY);

    return userLegalInfoEntity;
  }

}
