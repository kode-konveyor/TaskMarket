package com.kodekonveyor.market.payment;

import javax.annotation.Generated;

@Generated("by zenta-tools")
public class TransferTypeEntityTestData {

  public final static TransferTypeEntity get() {
    final TransferTypeEntity transferTypeEntity = new TransferTypeEntity();
    transferTypeEntity.setId(TransferTypeTestData.ID);
    transferTypeEntity
        .setTransferTypeName(TransferTypeTestData.TRANSFER_TYPE_NAME);
    transferTypeEntity.setBankIdLabel(TransferTypeTestData.BANK_ID_LABEL);
    transferTypeEntity.setAccountIdLabel(TransferTypeTestData.ACCOUNT_ID_LABEL);
    transferTypeEntity.setIsBankIdShown(TransferTypeTestData.IS_BANKID_SHOWN);

    return transferTypeEntity;
  };

}
