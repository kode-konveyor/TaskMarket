package com.kodekonveyor.market.payment;

import java.util.stream.Stream;

import com.kodekonveyor.market.ValidationException;

public class PaymentChannelUtil {

  public static void
      validatePaymentDetails(
          final PaymentDetailsDTO paymentDetailsDTO,
          final TransferTypeEntityRepository transferTypeEntityRepository
      ) {
    final Stream<PaymentDetailDTO> paymentDetailStream =
        paymentDetailsDTO.getPaymentDetail().stream();
    final Long sum = paymentDetailStream
        .map(PaymentDetailDTO::getPercentage).reduce(0L, Long::sum);
    validateHundredPercent(sum);
    validateTransferType(paymentDetailsDTO, transferTypeEntityRepository);
  }

  private static void validateHundredPercent(final Long sum) {
    if (!sum.equals(PaymentConstants.HUNDRED_PERCENT))
      throw new ValidationException(
          PaymentConstants.THE_SUM_OF_PAYMENTS_IS_NOT_100
      );
  }

  private static void validateTransferType(
      final PaymentDetailsDTO paymentDetailsDTO,
      final TransferTypeEntityRepository transferTypeEntityRepository
  ) {
    paymentDetailsDTO.getPaymentDetail().stream()
        .map(PaymentDetailDTO::getTransferType).distinct()
        .forEach((transferTypeId) -> {
          if (transferTypeEntityRepository.findById(transferTypeId).isEmpty())
            throw new ValidationException(
                PaymentConstants.INVALID_TRANSFER_TYPE + transferTypeId
            );
        });
  }
}
