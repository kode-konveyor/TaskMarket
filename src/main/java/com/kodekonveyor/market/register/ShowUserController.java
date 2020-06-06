package com.kodekonveyor.market.register;

import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodekonveyor.authentication.AuthenticatedUserService;
import com.kodekonveyor.authentication.UserEntity;
import com.kodekonveyor.market.UrlMapConstants;
import com.kodekonveyor.market.payment.PaymentDetailEntity;

@RestController
public class ShowUserController {

  @Autowired
  AuthenticatedUserService authenticatedUserService;

  @Autowired
  MarketUserEntityRepository marketUserEntityRepository;

  @Autowired
  Logger logger;

  @GetMapping(UrlMapConstants.SHOW_USER_PATH)
  public MarketUserDTO call() {
    final UserEntity userEntity = authenticatedUserService.call();

    final Optional<MarketUserEntity> entityP =
        marketUserEntityRepository.findByUser(userEntity);

    return entityP.map(this::copyEntityToDTO)
        .orElse(createDTOFromUserEntity(userEntity));
  }

  private MarketUserDTO createDTOFromUserEntity(final UserEntity userEntity) {
    final MarketUserDTO marketUserDTO = new MarketUserDTO();
    marketUserDTO.setUser(userEntity.getId());
    marketUserDTO.setLogin(userEntity.getLogin());
    return marketUserDTO;
  }

  private MarketUserDTO copyEntityToDTO(
      final MarketUserEntity entity
  ) {
    final MarketUserDTO marketUserDTO = new MarketUserDTO();
    marketUserDTO.setId(entity.getId());
    marketUserDTO.setPersonalName(entity.getPersonalName());
    marketUserDTO.setLegalName(entity.getLegalName());
    marketUserDTO.setBalanceInCents(entity.getBalanceInCents());
    marketUserDTO.setEmail(entity.getEmail());
    marketUserDTO.setIsTermsAccepted(entity.getIsTermsAccepted());
    marketUserDTO.setLegalAddress(entity.getLegalAddress());
    marketUserDTO.setUser(entity.getUser().getId());
    marketUserDTO.setLogin(entity.getUser().getLogin());
    marketUserDTO.setLegalForm(entity.getLegalForm().getId());
    marketUserDTO
        .setPaymentDetail(
            entity.getPaymentDetail().stream().map(PaymentDetailEntity::getId).collect(Collectors.toSet())
        );
    return marketUserDTO;
  }
}
