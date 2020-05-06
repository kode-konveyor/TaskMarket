package com.kodekonveyor.market.register;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Generated;

import com.kodekonveyor.authentication.UserTestData;
import com.kodekonveyor.market.payment.LegalFormTestData;
import com.kodekonveyor.market.payment.PaymentDetailTestData;

@Generated("by zenta-tools")
public class MarketUserDTOTestData {

  public final static MarketUserDTO get() {
    final MarketUserDTO marketUserDTO = new MarketUserDTO();
    marketUserDTO.setId(MarketUserTestData.ID);
    marketUserDTO.setPaymentDetail(Set.of(PaymentDetailTestData.ID));
    marketUserDTO.setIsTermsAccepted(MarketUserTestData.IS_TERMS_ACCEPTED);
    marketUserDTO.setEmail(MarketUserTestData.EMAIL);
    marketUserDTO.setLegalAddress(MarketUserTestData.LEGAL_ADDRESS);
    marketUserDTO.setLegalName(MarketUserTestData.LEGAL_NAME);
    marketUserDTO.setPersonalName(MarketUserTestData.PERSONAL_NAME);
    marketUserDTO.setUser(UserTestData.ID);
    marketUserDTO.setBalanceInCents(MarketUserTestData.BALANCE_IN_CENTS);
    marketUserDTO.setLegalForm(LegalFormTestData.ID);

    return marketUserDTO;
  }

  public static MarketUserDTO getInvalidRegime() {
    final MarketUserDTO marketUserDTO = get();
    marketUserDTO.setLegalForm(LegalFormTestData.ID_INVALID);
    return marketUserDTO;
  }

  public static MarketUserDTO getIdNull() {
    final MarketUserDTO marketUserDTO = get();
    marketUserDTO.setId(null);
    return marketUserDTO;
  }

  public static MarketUserDTO getIdNotInDatabase() {
    final MarketUserDTO marketUserDTO = get();
    marketUserDTO.setId(MarketUserTestData.ID_NOT_IN_DATABASE);
    marketUserDTO.setUser(UserTestData.ID_NO_MARKET_USER);
    marketUserDTO.setIsTermsAccepted(null);
    marketUserDTO.setEmail(null);
    marketUserDTO.setLegalAddress(null);
    marketUserDTO.setLegalName(null);
    marketUserDTO.setPersonalName(null);
    marketUserDTO.setBalanceInCents(null);
    marketUserDTO.setLegalForm(null);

    marketUserDTO.setPaymentDetail(new HashSet<>());

    return marketUserDTO;
  }

  public static MarketUserDTO getIdNotInNullDatabase() {
    final MarketUserDTO marketUserDTO = get();
    marketUserDTO.setId(MarketUserTestData.ID_NOT_IN_DATABASE);
    marketUserDTO.setUser(UserTestData.ID_IN_NULL_DATABASE);
    marketUserDTO.setPaymentDetail(new HashSet<>());

    return marketUserDTO;
  }

  public static MarketUserDTO getRoleTechnical() {
    final MarketUserDTO marketUserDTO = new MarketUserDTO();
    marketUserDTO.setId(MarketUserTestData.ID);
    marketUserDTO.setEmail(MarketUserTestData.EMAIL);
    marketUserDTO.setLegalName(MarketUserTestData.LEGAL_NAME);
    marketUserDTO.setIsTermsAccepted(MarketUserTestData.IS_TERMS_ACCEPTED);
    marketUserDTO.setPersonalName(MarketUserTestData.PERSONAL_NAME);
    marketUserDTO.setUser(UserTestData.ID_TECHNICAL);
    marketUserDTO.setPaymentDetail(new HashSet<>());
    return marketUserDTO;
  }

}
