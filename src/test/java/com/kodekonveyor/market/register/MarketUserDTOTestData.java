package com.kodekonveyor.market.register;

import java.util.Set;

import javax.annotation.Generated;

import com.kodekonveyor.authentication.UserDTOTestData;
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
    marketUserDTO.setUser(UserDTOTestData.get());
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
    marketUserDTO.setId(null);
    marketUserDTO.setUser(UserDTOTestData.getNotInDatabase());
    marketUserDTO.setIsTermsAccepted(null);
    marketUserDTO.setEmail(null);
    marketUserDTO.setLegalAddress(null);
    marketUserDTO.setLegalName(null);
    marketUserDTO.setPersonalName(null);
    marketUserDTO.setBalanceInCents(null);
    marketUserDTO.setLegalForm(null);
    marketUserDTO.setPaymentDetail(null);

    return marketUserDTO;
  }

  public static MarketUserDTO getRoleCanBePaid() {
    final MarketUserDTO marketUserDTO = get();
    marketUserDTO.setUser(UserDTOTestData.getRegistered());
    marketUserDTO.setId(MarketUserTestData.ID_CAN_BE_PAID);
    return marketUserDTO;
  }
}
