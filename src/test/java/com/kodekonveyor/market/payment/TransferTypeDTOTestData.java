package com.kodekonveyor.market.payment;

import javax.annotation.Generated;

@Generated("by zenta-tools")
public class TransferTypeDTOTestData {

  public final static TransferTypeDTO get() {
    final TransferTypeDTO transferTypeDTO = new TransferTypeDTO();
    transferTypeDTO.setId(TransferTypeTestData.ID);
    transferTypeDTO
        .setTransferTypeName(TransferTypeTestData.TRANSFER_TYPE_NAME);
    transferTypeDTO.setBankIdLabel(TransferTypeTestData.BANK_ID_LABEL);
    transferTypeDTO.setAccountIdLabel(TransferTypeTestData.ACCOUNT_ID_LABEL);
    transferTypeDTO.setIsBankIdShown(TransferTypeTestData.IS_BANKID_SHOWN);

    return transferTypeDTO;
  };

}
