package com.kodekonveyor.market.payment;

import javax.annotation.Generated;

@Generated("by zenta-tools")
public class PaymentDetailEntityTestData {

  public final static PaymentDetailEntity get() {
    final PaymentDetailEntity paymentDetailEntity = new PaymentDetailEntity();
    paymentDetailEntity.setId(PaymentDetailTestData.ID);
    paymentDetailEntity.setTransferType(TransferTypeEntityTestData.get());
    paymentDetailEntity.setAccountId(PaymentDetailTestData.ACCOUNT_ID);
    paymentDetailEntity.setBankId(PaymentDetailTestData.BANK_ID);
    paymentDetailEntity.setName(PaymentDetailTestData.NAME);
    paymentDetailEntity.setPercentage(PaymentDetailTestData.PERCENTAGE);

    return paymentDetailEntity;
  };

}
