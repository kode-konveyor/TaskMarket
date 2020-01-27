package com.kodekonveyor.market.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kodekonveyor.authentication.AuthenticatedUserService;
import com.kodekonveyor.authentication.UserEntity;
import com.kodekonveyor.market.MarketConstants;
import com.kodekonveyor.market.UrlMapConstants;
import com.kodekonveyor.market.ValidationException;

@RestController
public class RegistrationController {

  @Autowired
  AuthenticatedUserService authenticatedUserService;

  @Autowired
  MarketUserEntityRepository marketUserEntityRepository;

  @PostMapping(UrlMapConstants.REGISTER_USER_PATH)
  public void
      call(final @RequestBody RegistrationInfoDTO registrationInfoDTO) {
    checkRegimeValidity(registrationInfoDTO);
    doStore(registrationInfoDTO);

  }

  private void
      checkRegimeValidity(final RegistrationInfoDTO registrationInfoDTO) {
    if (
      !registrationInfoDTO.getPaymentRegime()
          .equals(MarketConstants.PAYMENT_REGIME)
    )
      throw new ValidationException(
          MarketConstants.INVALID_PAYMENT_REGIME_EXCEPTION
      );
  }

  private void doStore(final RegistrationInfoDTO registrationInfoDTO) {
    final UserEntity userEntity = authenticatedUserService.call();

    MarketUserEntity entity;

    entity = new MarketUserEntity();
    final UserLegalInfoEntity legal = new UserLegalInfoEntity();
    legal.setCountry(registrationInfoDTO.getCountry());
    legal.setEmail(registrationInfoDTO.getEmail());
    legal.setLegalAddress(registrationInfoDTO.getLegalAddress());
    legal.setLegalName(registrationInfoDTO.getLegalName());
    legal.setPaymentDetails(registrationInfoDTO.getPaymentDetails());
    legal.setPaymentRegime(registrationInfoDTO.getPaymentRegime());

    entity.setLegal(legal);
    entity.setLogin(userEntity);

    marketUserEntityRepository.save(entity);

  }

}
