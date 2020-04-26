package com.kodekonveyor.market.payment;

import javax.annotation.Generated;

@Generated("by zenta-tools")
public class PaymentDetailDTOTestData {

  public final static PaymentDetailDTO get() {
    final PaymentDetailDTO paymentDetailDTO = new PaymentDetailDTO();
    paymentDetailDTO.setId(PaymentDetailTestData.ID);
    paymentDetailDTO.setTransferType(TransferTypeTestData.ID);
    paymentDetailDTO.setAccountId(PaymentDetailTestData.ACCOUNT_ID);
    paymentDetailDTO.setBankId(PaymentDetailTestData.BANK_ID);
    paymentDetailDTO.setName(PaymentDetailTestData.NAME);
    paymentDetailDTO.setPercentage(PaymentDetailTestData.PERCENTAGE);

    return paymentDetailDTO;
  };

  public final static PaymentDetailDTO list() {
    final PaymentDetailDTO paymentDetailDTO = new PaymentDetailDTO();
    paymentDetailDTO.setId(PaymentDetailTestData.ID);

    return paymentDetailDTO;
  }

  public final static PaymentDetailDTO getPercentageFourty() {
    final PaymentDetailDTO paymentDetailDTO = get();
    paymentDetailDTO.setPercentage(PaymentDetailTestData.PERCENTAGE_40);
    return paymentDetailDTO;
  };

  public final static PaymentDetailDTO getPercentageSixty() {
    final PaymentDetailDTO paymentDetailDTO = get();
    paymentDetailDTO.setPercentage(PaymentDetailTestData.PERCENTAGE_60);
    return paymentDetailDTO;
  };

  public final static PaymentDetailDTO getPercentageFifty() {
    final PaymentDetailDTO paymentDetailDTO = get();
    paymentDetailDTO.setPercentage(PaymentDetailTestData.PERCENTAGE_50);
    return paymentDetailDTO;
  }

  public static PaymentDetailDTO getPercentageSixtyWithInvalidChannel() {
    final PaymentDetailDTO paymentDetailDTO = getPercentageSixty();
    paymentDetailDTO.setTransferType(TransferTypeTestData.ID_NONEXISTENT);
    return paymentDetailDTO;
  };

}
