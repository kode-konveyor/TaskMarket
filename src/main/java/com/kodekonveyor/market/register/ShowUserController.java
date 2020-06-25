package com.kodekonveyor.market.register;

import com.kodekonveyor.authentication.AuthenticatedUserService;
import com.kodekonveyor.authentication.UserEntity;
import com.kodekonveyor.authentication.UserEntityRepository;
import com.kodekonveyor.logging.LoggingMarkerConstants;
import com.kodekonveyor.market.MarketConstants;
import com.kodekonveyor.market.UrlMapConstants;
import com.kodekonveyor.market.ValidationException;
import com.kodekonveyor.market.lead.CheckRoleUtil;
import com.kodekonveyor.market.payment.PaymentDetailEntity;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.kodekonveyor.market.register.RegisterConstants.CONTRACT_ROLE;
import static com.kodekonveyor.market.register.RegisterConstants.LOG_SHOW_MARKET_USER_FAILURE;
import static com.kodekonveyor.market.register.RegisterConstants.TECHNICAL_ROLE;
import static com.kodekonveyor.market.register.RegisterConstants.USER_NOT_FOUND;

@RestController
public class ShowUserController {

  @Autowired
  AuthenticatedUserService authenticatedUserService;

  @Autowired
  MarketUserEntityRepository marketUserEntityRepository;

  @Autowired
  UserEntityRepository userEntityRepository;

  @Autowired
  Logger logger;

  @GetMapping(UrlMapConstants.SHOW_USER_PATH)
  public MarketUserDTO call(final String login) {
    final UserEntity sessionUser = authenticatedUserService.call();
    logger.info(LoggingMarkerConstants.REGISTER, sessionUser.toString());

    final UserEntity userFound;
    if (StringUtils.isBlank(login)) {
      userFound = sessionUser;
    } else {
      final Optional<UserEntity> existingUser = userEntityRepository.findByLogin(login);
      if (existingUser.isPresent()) {
        userFound = existingUser.get();
      } else {
        logger.warn(LoggingMarkerConstants.REGISTER, LOG_SHOW_MARKET_USER_FAILURE, login, USER_NOT_FOUND);
        throw new ValidationException(USER_NOT_FOUND);
      }
    }
    final MarketUserDTO marketUserDTO;
    final Optional<MarketUserEntity> marketUserFound =
            marketUserEntityRepository.findByUser(userFound);
    if (marketUserFound.isPresent()) {
      marketUserDTO = copyEntityToDTO(sessionUser, marketUserFound.get());
    } else {
      marketUserDTO = createDTOFromUserEntity(userFound);
    }

    logger.debug(
            LoggingMarkerConstants.REGISTER,
            MarketConstants.MARKET_USER_RETURNED_SUCCESSFULLY +
                    marketUserDTO.getId()
    );
    return marketUserDTO;
  }

  private MarketUserDTO createDTOFromUserEntity(final UserEntity userEntity) {
    final MarketUserDTO marketUserDTO = new MarketUserDTO();
    marketUserDTO.setUser(userEntity.getId());
    marketUserDTO.setLogin(userEntity.getLogin());
    return marketUserDTO;
  }

  private MarketUserDTO copyEntityToDTO(final UserEntity sessionUser, final MarketUserEntity entity){
    final UserEntity user = entity.getUser();
    final MarketUserDTO marketUserDTO = new MarketUserDTO();

    final boolean isSelf = Objects.equals(sessionUser.getId(), user.getId());
    final boolean hasTechnicalRole = CheckRoleUtil.hasAnyRole(sessionUser, TECHNICAL_ROLE);
    final boolean hasContractRole = CheckRoleUtil.hasAnyRole(sessionUser, CONTRACT_ROLE);

    marketUserDTO.setId(entity.getId());
    marketUserDTO.setUser(entity.getUser().getId());
    marketUserDTO.setLogin(entity.getUser().getLogin());

    if (isSelf) {
      marketUserDTO.setBalanceInCents(entity.getBalanceInCents());
    }

    if (isSelf || hasContractRole) {
      marketUserDTO.setEmail(entity.getEmail());
      marketUserDTO.setPersonalName(entity.getPersonalName());
      marketUserDTO.setLegalForm(entity.getLegalForm().getId());
      marketUserDTO.setLegalAddress(entity.getLegalAddress());
      marketUserDTO.setIsTermsAccepted(entity.getIsTermsAccepted());
      marketUserDTO
              .setPaymentDetail(
                      entity.getPaymentDetail().stream().map(PaymentDetailEntity::getId).collect(Collectors.toSet())
              );
    }

    if (isSelf || hasContractRole || hasTechnicalRole) {
      marketUserDTO.setLegalName(entity.getLegalName());
    }

    return marketUserDTO;
  }
}
