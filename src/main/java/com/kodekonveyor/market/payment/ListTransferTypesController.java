package com.kodekonveyor.market.payment;

import com.kodekonveyor.market.UrlMapConstants;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.kodekonveyor.logging.LoggingMarkerConstants.PAYMENT;
import static com.kodekonveyor.market.payment.PaymentConstants.LOG_LIST_TRANSFR_TYPES_CALL;
import static com.kodekonveyor.market.payment.PaymentConstants.LOG_LIST_TRANSFR_TYPES_SUCCESS_CALL;

@RestController
public class ListTransferTypesController {

  @Autowired
  TransferTypeEntityRepository transferTypeEntityRepository;

  @Autowired
  Logger logger;

  @GetMapping(UrlMapConstants.TRANSFER_TYPES_PUBLIC_PATH)
  public List<TransferTypeDTO> call() {
    logger.info(PAYMENT, LOG_LIST_TRANSFR_TYPES_CALL);
    final Iterable<TransferTypeEntity> allTransferType =
        transferTypeEntityRepository.findAll();
    List<TransferTypeDTO> paymentTypesDTO = StreamSupport.stream(allTransferType.spliterator(), false)
            .map(this::getTransferTypeDTO)
            .collect(Collectors.toList());
    logger.debug(PAYMENT, LOG_LIST_TRANSFR_TYPES_SUCCESS_CALL);
    return paymentTypesDTO;
  }

  private TransferTypeDTO
      getTransferTypeDTO(final TransferTypeEntity transferType) {
    final TransferTypeDTO transferTypeDTO = new TransferTypeDTO();
    transferTypeDTO.setAccountIdLabel(transferType.getAccountIdLabel());
    transferTypeDTO.setBankIdLabel(transferType.getBankIdLabel());
    transferTypeDTO.setId(transferType.getId());
    transferTypeDTO.setIsBankIdShown(transferType.getIsBankIdShown());
    transferTypeDTO.setTransferTypeName(transferType.getTransferTypeName());
    return transferTypeDTO;
  }
}
