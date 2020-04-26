package com.kodekonveyor.market.payment;

import javax.annotation.Generated;

@Generated("by zenta-tools")
public class LegalFormEntityTestData {

  public final static LegalFormEntity get() {
    final LegalFormEntity legalFormEntity = new LegalFormEntity();
    legalFormEntity.setId(LegalFormTestData.ID);
    legalFormEntity.setLegalFormName(LegalFormTestData.LEGAL_FORM_NAME);
    legalFormEntity.setCountry(LegalFormTestData.COUNTRY);

    return legalFormEntity;
  };

}
