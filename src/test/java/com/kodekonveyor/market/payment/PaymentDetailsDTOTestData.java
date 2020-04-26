package com.kodekonveyor.market.payment;

import java.util.Set;

public class PaymentDetailsDTOTestData {

  public static PaymentDetailsDTO get() {
    final PaymentDetailsDTO paymentDetailsDTO = new PaymentDetailsDTO();
    paymentDetailsDTO.setPaymentDetail(Set.of(PaymentDetailDTOTestData.get()));
    return paymentDetailsDTO;
  }

  public static PaymentDetailsDTO getSumLessThanHundredPercent() {
    final PaymentDetailsDTO paymentDetailsDTO = get();
    paymentDetailsDTO.setPaymentDetail(
        Set.of(PaymentDetailDTOTestData.getPercentageFourty())
    );
    return paymentDetailsDTO;
  }

  public static PaymentDetailsDTO getSumHundredPercentInTwoChannels() {
    final PaymentDetailsDTO paymentDetailsDTO = get();
    paymentDetailsDTO.setPaymentDetail(
        Set.of(
            PaymentDetailDTOTestData.getPercentageFourty(),
            PaymentDetailDTOTestData.getPercentageSixty()
        )
    );
    return paymentDetailsDTO;
  }

  public static PaymentDetailsDTO getSumLessThanHundredPercentInTwoChannels() {
    final PaymentDetailsDTO paymentDetailsDTO = get();
    paymentDetailsDTO.setPaymentDetail(
        Set.of(
            PaymentDetailDTOTestData.getPercentageFourty(),
            PaymentDetailDTOTestData.getPercentageFifty()
        )
    );
    return paymentDetailsDTO;
  }

  public static PaymentDetailsDTO getSumMoreThanHundredPercentInTwoChannels() {
    final PaymentDetailsDTO paymentDetailsDTO = get();
    paymentDetailsDTO.setPaymentDetail(
        Set.of(
            PaymentDetailDTOTestData.getPercentageSixty(),
            PaymentDetailDTOTestData.getPercentageFifty()
        )
    );
    return paymentDetailsDTO;
  }

  public static PaymentDetailsDTO getWithInvalidChannel() {
    final PaymentDetailsDTO paymentDetailsDTO = get();
    paymentDetailsDTO.setPaymentDetail(
        Set.of(
            PaymentDetailDTOTestData.getPercentageFourty(),
            PaymentDetailDTOTestData.getPercentageSixtyWithInvalidChannel()
        )
    );
    return paymentDetailsDTO;
  }

}
