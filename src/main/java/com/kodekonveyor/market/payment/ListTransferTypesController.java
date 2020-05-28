package com.kodekonveyor.market.payment;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodekonveyor.market.UrlMapConstants;

@RestController
public class ListTransferTypesController {

  @Autowired
  TransferTypeEntityRepository transferTypeEntityRepository;

  @GetMapping(UrlMapConstants.TRANSFER_TYPES_PUBLIC_PATH)
  public List<TransferTypeDTO> call() {

    final Iterable<TransferTypeEntity> allTransferType =
        transferTypeEntityRepository.findAll();
    return StreamSupport.stream(allTransferType.spliterator(), false)
        .map(this::getTransferTypeDTO)
        .collect(Collectors.toList());
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
