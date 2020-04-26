package com.kodekonveyor.market.payment;

import java.util.Set;

import lombok.Data;

@Data
public class PaymentDetailsDTO {

  Set<PaymentDetailDTO> paymentDetail;

}
