package com.kodekonveyor.market.register;

import com.kodekonveyor.authentication.UserTestData;

public class RegisterTestData {

  public final String ADDRESS = "Lectroid str. 25, 12345, Shelby, Alabama, US";

  public final String EMAIL = "john.bigboot@example.com";

  public final String LEGALNAME = "John Bigbooté";

  public final String REGIME_ROW = "restofworld";

  public final String PAYMENT_DETAILS = "paypal:john.bigboot@example.com";

  public final String COUNTRY = "es";

  public final RegistrationInfoDTO TEST_REGISTRATION_INFO;

  public final String PROJECTNAME = "kode-konveyor/example";

  public final String PROJECTROLE = "coder";

  public final UserLegalInfoEntity USER_LEGAL_INFO;

  public final MarketUserEntity MARKET_USER;

  public final MarketUserDTO MARKET_USER_DTO;

  public final String NEXT_URL = "/foo.html";

  private final UserTestData userTestData;

  public final MarketUserDTO MARKET_USER_DTO_NEWLY_CREATED;

  public RegisterTestData(final UserTestData userTestData) {
    this.userTestData = userTestData;
    USER_LEGAL_INFO = createUSER_LEGAL_INFO();
    MARKET_USER = createMARKET_USER();
    TEST_REGISTRATION_INFO = createTEST_REGISTRATION_INFO();
    MARKET_USER_DTO = createMARKET_USER_DTO();
    MARKET_USER_DTO_NEWLY_CREATED = createMARKET_USER_DTO_NEWLY_CREATED();
  }

  private MarketUserDTO createMARKET_USER_DTO_NEWLY_CREATED() {
    final MarketUserDTO marketUserDTO = new MarketUserDTO();
    marketUserDTO.setRegistrationInfo(new RegistrationInfoDTO());
    marketUserDTO.setLogin(userTestData.USER_WITH_NO_MARKET_USER_ID_LOGIN);
    return marketUserDTO;
  }

  private MarketUserDTO createMARKET_USER_DTO() {
    final MarketUserDTO marketUserDTO = new MarketUserDTO();
    marketUserDTO.setRegistrationInfo(createTEST_REGISTRATION_INFO());
    marketUserDTO.setLogin(userTestData.GITHUB_ID);
    return marketUserDTO;
  }

  private MarketUserEntity createMARKET_USER() {
    final MarketUserEntity marketUserEntity = new MarketUserEntity();
    marketUserEntity.setLegal(USER_LEGAL_INFO);
    marketUserEntity.setLogin(userTestData.TEST_USER_ENTITY);
    return marketUserEntity;
  }

  private UserLegalInfoEntity createUSER_LEGAL_INFO() {
    final UserLegalInfoEntity userLegalInfoEntity = new UserLegalInfoEntity();
    userLegalInfoEntity.setLegalAddress(ADDRESS);
    userLegalInfoEntity.setEmail(EMAIL);
    userLegalInfoEntity.setLegalName(LEGALNAME);
    userLegalInfoEntity.setPaymentRegime(REGIME_ROW);
    userLegalInfoEntity.setPaymentDetails(PAYMENT_DETAILS);
    userLegalInfoEntity.setCountry(COUNTRY);

    return userLegalInfoEntity;
  }

  private RegistrationInfoDTO createTEST_REGISTRATION_INFO() {
    final RegistrationInfoDTO registrationInfoDTO = new RegistrationInfoDTO();
    registrationInfoDTO.setLegalAddress(ADDRESS);
    registrationInfoDTO.setEmail(EMAIL);
    registrationInfoDTO.setLegalName(LEGALNAME);
    registrationInfoDTO.setPaymentRegime(REGIME_ROW);
    registrationInfoDTO.setPaymentDetails(PAYMENT_DETAILS);
    registrationInfoDTO.setCountry(COUNTRY);
    return registrationInfoDTO;
  }
}
