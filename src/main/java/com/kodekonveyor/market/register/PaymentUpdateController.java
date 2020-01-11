package com.kodekonveyor.market.register;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.kodekonveyor.authentication.AuthenticatedUserService;
import com.kodekonveyor.authentication.UserEntity;

@Controller
public class PaymentUpdateController {

  @Autowired
  private AuthenticatedUserService authenticatedUserService;

  @Autowired
  private MarketUserEntityRepository marketUserEntityRepository;

  @Autowired

  public MarketUserDTO call(final String paymentDetails) {
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
        .setPaymentDetails(paymentDetails);
    registrationInfoDTO.setPaymentRegime(legal.getPaymentRegime());
    marketUserDTO.setRegistrationInfo(registrationInfoDTO);
    PaymentChannelUtil.validatePayment(
        marketUserDTO.getRegistrationInfo().getPaymentDetails()
    );
    return marketUserDTO;

  }

}
