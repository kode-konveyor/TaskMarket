package com.kodekonveyor.market.register;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodekonveyor.authentication.AuthenticatedUserService;
import com.kodekonveyor.authentication.RoleConstants;
import com.kodekonveyor.authentication.RoleEntity;
import com.kodekonveyor.authentication.RoleEntityRepository;
import com.kodekonveyor.authentication.UserEntity;
import com.kodekonveyor.authentication.UserEntityRepository;
import com.kodekonveyor.market.UnauthorizedException;
import com.kodekonveyor.market.UrlMapConstants;
import com.kodekonveyor.market.lead.CheckRoleUtil;
import com.kodekonveyor.market.payment.PaymentDetailEntity;

@RestController
public class ListRegisteredUsersController {

  @Autowired
  MarketUserEntityRepository marketUserEntityRepository;

  @Autowired
  UserEntityRepository userEntityRepository;

  @Autowired
  RoleEntityRepository roleEntityRepository;

  @Autowired
  AuthenticatedUserService authenticatedUserService;

  @GetMapping(UrlMapConstants.LIST_REGISTERED_USERS_PATH)
  public List<MarketUserDTO> call() {
    final UserEntity sessionUser = authenticatedUserService.call();
    validateAuthorization(sessionUser);
    final RoleEntity role = roleEntityRepository
        .findByName(RegisterConstants.REGISTERED_ROLE).get();

    final List<UserEntity> userList = userEntityRepository.findByRole(role);

    final List<MarketUserDTO> registeredMarketUserList = new ArrayList<>();

    for (final UserEntity user : userList) {

      final MarketUserEntity entity =
          marketUserEntityRepository.findByUser(user).get();
      registeredMarketUserList.add(convertEntityToDTO(entity));
    }

    return registeredMarketUserList;
  }

  private void validateAuthorization(final UserEntity user) {
    if (
      !CheckRoleUtil.hasAnyRole(
          user, RoleConstants.ROLE_CONTRACT, RoleConstants.ROLE_PROJECT_MANAGER,
          RoleConstants.ROLE_MANAGER, RoleConstants.ROLE_TECHNICAL
      )
    )
      throw new UnauthorizedException(RegisterConstants.NO_VALID_ROLE);

  }

  private MarketUserDTO convertEntityToDTO(final MarketUserEntity entity) {
    final MarketUserDTO dto = new MarketUserDTO();
    dto.setBalanceInCents(entity.getBalanceInCents());
    dto.setEmail(entity.getEmail());
    dto.setId(entity.getId());
    dto.setIsTermsAccepted(entity.getIsTermsAccepted());
    dto.setLegalAddress(entity.getLegalAddress());
    dto.setLegalForm(entity.getLegalForm().getId());
    dto.setLegalName(entity.getLegalName());
    dto.setLogin(entity.getUser().getLogin());
    dto.setPaymentDetail(
        entity.getPaymentDetail().stream().map(PaymentDetailEntity::getId).collect(Collectors.toSet())
    );
    dto.setPersonalName(entity.getPersonalName());
    dto.setUser(entity.getUser().getId());
    return dto;
  }

}
