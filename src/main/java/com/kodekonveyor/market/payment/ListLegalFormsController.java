package com.kodekonveyor.market.payment;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodekonveyor.logging.LoggingMarkerConstants;
import com.kodekonveyor.market.UrlMapConstants;

@RestController
public class ListLegalFormsController {

  @Autowired
  LegalFormEntityRepository legalFormEntityRepository;

  @Autowired
  Logger logger;

  @GetMapping(UrlMapConstants.LIST_LEGAL_FORMS_PATH)
  public List<LegalFormDTO> call() {
    final Iterable<LegalFormEntity> allLegalForms =
        legalFormEntityRepository.findAll();

    final List<LegalFormDTO> allLegalFormsDTO =
        StreamSupport.stream(allLegalForms.spliterator(), false)
            .map(this::convertLegalFormToDTO)
            .collect(Collectors.toList());
    logger.info(
        LoggingMarkerConstants.PAYMENT, PaymentConstants.LEGAL_FORMS,
        allLegalFormsDTO.toString()
    );
    return allLegalFormsDTO;
  }

  private LegalFormDTO convertLegalFormToDTO(final LegalFormEntity entity) {
    final LegalFormDTO result = new LegalFormDTO();
    result.setId(entity.getId());
    result.setCountry(entity.getCountry());
    result.setLegalFormName(entity.getLegalFormName());

    return result;
  }

}
