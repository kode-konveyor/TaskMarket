package com.kodekonveyor.market.register;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodekonveyor.authentication.AuthenticatedUserService;
import com.kodekonveyor.authentication.UserEntity;
import com.kodekonveyor.market.MarketConstants;
import com.kodekonveyor.market.UnauthorizedException;
import com.kodekonveyor.market.UrlMapConstants;
import com.kodekonveyor.market.lead.CheckRoleUtil;

@RestController
public class PaymentUpdateController {

  @Autowired
  AuthenticatedUserService authenticatedUserService;

  @Autowired
  MarketUserEntityRepository marketUserEntityRepository;

  @PutMapping(UrlMapConstants.PAYMENT_UPDATE_PATH)
  public Object call(final String paymentDetails) {
    final UserEntity user = authenticatedUserService.call();
    PaymentChannelUtil.validatePaymentDetails(paymentDetails);

    registrationNeeded(user);

    if (!CheckRoleUtil.hasRole(user, MarketConstants.KODEKONVEYOR_CONTRACT))
      throw new UnauthorizedException(
          RegisterConstants.UNAUTHORIZED_NOT_ENOUGH_RIGHTS
      );

    final List<MarketUserEntity> marketuserList =
        marketUserEntityRepository.findByLogin(user);
    contractAcceptance(marketuserList);

    return null;

  }

  private void contractAcceptance(final List<MarketUserEntity> marketuserList) {
    if (!marketuserList.get(0).getLegal().isContractTermsAccepted())
      throw new UnauthorizedException(
          RegisterConstants.CONTRACT_TERMS_NOT_ACCEPTED
      );
  }

  private void
      registrationNeeded(final UserEntity user) {
    if (
      !CheckRoleUtil.hasRole(user, MarketConstants.CAN_BE_PAID_ROLE)

    )
      throw new UnauthorizedException(RegisterConstants.IN_PAYMENT_UPDATE);

  }

}
