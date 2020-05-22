package com.kodekonveyor.market.register;

import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kodekonveyor.authentication.AuthenticatedUserService;
import com.kodekonveyor.authentication.UserEntity;
import com.kodekonveyor.market.UrlMapConstants;
import com.kodekonveyor.market.ValidationException;
import com.kodekonveyor.market.payment.LegalFormEntity;
import com.kodekonveyor.market.payment.LegalFormEntityRepository;

@RestController
public class RegistrationController {

  @Autowired
  AuthenticatedUserService authenticatedUserService;

  @Autowired
  MarketUserEntityRepository marketUserEntityRepository;

  @Autowired
  LegalFormEntityRepository legalFormEntityRepository;

  @PostMapping(UrlMapConstants.REGISTER_USER_PATH)
  public MarketUserDTO
      call(final @RequestBody MarketUserDTO marketUserDTO) {
    doStore(marketUserDTO);
    return marketUserDTO;
  }

  private void doStore(final MarketUserDTO marketUserDTO) {

    final Optional<LegalFormEntity> legalform =
        legalFormEntityRepository.findById(marketUserDTO.getLegalForm());
    if (legalform.isEmpty())
      throw new ValidationException(RegisterConstants.NO_SUCH_LEGAL_FORM);
    final UserEntity userEntity = authenticatedUserService.call();

    final MarketUserEntity entity =
        createMarketUserEntity(marketUserDTO, legalform, userEntity);

    marketUserEntityRepository.save(entity);

  }

  private MarketUserEntity createMarketUserEntity(
      final MarketUserDTO marketUserDTO,
      final Optional<LegalFormEntity> legalForm, final UserEntity userEntity
  ) {
    final MarketUserEntity entity = new MarketUserEntity();
    entity.setBalanceInCents(0L);
    entity.setIsTermsAccepted(marketUserDTO.getIsTermsAccepted());
    entity.setEmail(marketUserDTO.getEmail());
    entity.setLegalAddress(marketUserDTO.getLegalAddress());
    entity.setLegalName(marketUserDTO.getLegalName());
    entity.setPersonalName(marketUserDTO.getLegalName());
    entity.setUser(userEntity);
    entity.setLegalForm(legalForm.get());
    entity.setPaymentDetail(new HashSet<>());
    return entity;
  }

}
