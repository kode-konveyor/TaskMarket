package com.kodekonveyor.market.register;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodekonveyor.authentication.AuthenticatedUserService;
import com.kodekonveyor.authentication.UserEntity;
import com.kodekonveyor.market.UrlMapConstants;

@RestController
public class ShowUserController {

  @Autowired
  AuthenticatedUserService authenticatedUserService;

  @Autowired
  MarketUserEntityRepository marketUserEntityRepository;

  @GetMapping(UrlMapConstants.SHOW_USER_PATH)
  public MarketUserDTO call() {
    final UserEntity userEntity = authenticatedUserService.call();

    final List<MarketUserEntity> entities =
        marketUserEntityRepository.findByLogin(userEntity);
    MarketUserEntity entity;
    if (entities.isEmpty()) {
      entity = new MarketUserEntity();
      entity.setLegal(new UserLegalInfoEntity());
    } else
      entity = entities.get(0);
    final MarketUserDTO marketUserDTO = new MarketUserDTO();
    marketUserDTO.setLogin(userEntity.getLogin());
    final RegistrationInfoDTO registrationInfoDTO = new RegistrationInfoDTO();
    final UserLegalInfoEntity legal = entity.getLegal();
    registrationInfoDTO.setCountry(legal.getCountry());
    registrationInfoDTO.setEmail(legal.getEmail());
    registrationInfoDTO.setLegalAddress(legal.getLegalAddress());
    registrationInfoDTO.setLegalName(legal.getLegalName());
    registrationInfoDTO
        .setPaymentDetails(legal.getPaymentDetails());
    registrationInfoDTO.setPaymentRegime(legal.getPaymentRegime());
    marketUserDTO.setRegistrationInfo(registrationInfoDTO);
    return marketUserDTO;
  }

}
