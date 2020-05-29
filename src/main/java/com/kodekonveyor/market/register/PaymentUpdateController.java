package com.kodekonveyor.market.register;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodekonveyor.authentication.AuthenticatedUserService;
import com.kodekonveyor.authentication.UserEntity;
import com.kodekonveyor.logging.LoggingMarkerConstants;
import com.kodekonveyor.market.MarketConstants;
import com.kodekonveyor.market.UnauthorizedException;
import com.kodekonveyor.market.UrlMapConstants;
import com.kodekonveyor.market.lead.CheckRoleUtil;
import com.kodekonveyor.market.payment.PaymentChannelUtil;
import com.kodekonveyor.market.payment.PaymentDetailsDTO;
import com.kodekonveyor.market.payment.TransferTypeEntityRepository;
import com.kodekonveyor.market.project.ProjectEntity;
import com.kodekonveyor.market.project.ProjectEntityRepository;

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

  @Autowired
  Logger logger;

  @PutMapping(UrlMapConstants.PAYMENT_UPDATE_PATH)
  public Object call(final PaymentDetailsDTO paymentDetailsDTO) {
    logger.info(
        LoggingMarkerConstants.REGISTER,
        paymentDetailsDTO.toString()
    );

    final UserEntity user = authenticatedUserService.call();
    PaymentChannelUtil.validatePaymentDetails(
        paymentDetailsDTO, transferTypeEntityRepository
    );

    final ProjectEntity project =
        projectEntityRepository
            .findByName(MarketConstants.KODE_KONVEYOR_PROJECT_NAME).get();
    if (
      !CheckRoleUtil.hasRole(user, project, MarketConstants.CAN_BE_PAID_ROLE)

    ) {
      logger.error(
          LoggingMarkerConstants.REGISTER,
          RegisterConstants.NO_CAN_BE_PAID_ROLE + user
      );
      throw new UnauthorizedException(
          RegisterConstants.NO_CAN_BE_PAID_ROLE + user
      );
    }
    final MarketUserEntity marketuser =
        marketUserEntityRepository.findByUser(user).get();
    contractAcceptance(marketuser);
    logger.info(
        LoggingMarkerConstants.REGISTER,
        RegisterConstants.PAYMENT_UPDATED_SUCCESSFULLY
    );
    return null;

  }

  private void contractAcceptance(final MarketUserEntity marketuser) {
    if (!marketuser.getIsTermsAccepted()) {
      logger.error(
          LoggingMarkerConstants.REGISTER,
          RegisterConstants.CONTRACT_TERMS_NOT_ACCEPTED
      );
      throw new UnauthorizedException(
          RegisterConstants.CONTRACT_TERMS_NOT_ACCEPTED
      );
    }
  }

}
