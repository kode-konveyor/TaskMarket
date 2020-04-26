package com.kodekonveyor.market.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodekonveyor.authentication.AuthenticatedUserService;
import com.kodekonveyor.authentication.UserEntity;
import com.kodekonveyor.market.MarketConstants;
import com.kodekonveyor.market.UnauthorizedException;
import com.kodekonveyor.market.UrlMapConstants;
import com.kodekonveyor.market.lead.CheckRoleUtil;
import com.kodekonveyor.market.project.ProjectEntity;
import com.kodekonveyor.market.project.ProjectEntityRepository;
import com.kodekonveyor.market.register.MarketUserEntity;
import com.kodekonveyor.market.register.MarketUserEntityRepository;
import com.kodekonveyor.market.register.RegisterConstants;

@RestController
public class PaymentUpdateController {

  @Autowired
  AuthenticatedUserService authenticatedUserService;

  @Autowired
  MarketUserEntityRepository marketUserEntityRepository;

  @Autowired
  ProjectEntityRepository projectEntityRepository;

  @Autowired
  TransferTypeEntityRepository transferTypeEntityRepository;

  @PutMapping(UrlMapConstants.PAYMENT_UPDATE_PATH)
  public Object call(final PaymentDetailsDTO paymentDetailsDTO) {
    final UserEntity user = authenticatedUserService.call();
    PaymentChannelUtil.validatePaymentDetails(
        paymentDetailsDTO, transferTypeEntityRepository
    );

    final ProjectEntity project =
        projectEntityRepository
            .findByName(MarketConstants.KODE_KONVEYOR_PROJECT_NAME).get();
    if (
      !CheckRoleUtil.hasRole(user, project, MarketConstants.CAN_BE_PAID_ROLE)

    )
      throw new UnauthorizedException(
          RegisterConstants.NO_CAN_BE_PAID_ROLE + user
      );

    final MarketUserEntity marketuser =
        marketUserEntityRepository.findByUser(user).get();
    contractAcceptance(marketuser);

    return null;

  }

  private void contractAcceptance(final MarketUserEntity marketuser) {
    if (!marketuser.getIsTermsAccepted())
      throw new UnauthorizedException(
          RegisterConstants.CONTRACT_TERMS_NOT_ACCEPTED
      );
  }

}
