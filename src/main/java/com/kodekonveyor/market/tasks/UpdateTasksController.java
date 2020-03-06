package com.kodekonveyor.market.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.kodekonveyor.authentication.AuthenticatedUserService;
import com.kodekonveyor.authentication.UserEntity;
import com.kodekonveyor.market.MarketConstants;
import com.kodekonveyor.market.UnauthorizedException;
import com.kodekonveyor.market.lead.CheckRoleUtil;
import com.kodekonveyor.market.register.RegisterConstants;

@RestController
public class UpdateTasksController {

  @Autowired
  AuthenticatedUserService authenticatedUserService;

  public Object call() {
    final UserEntity user = authenticatedUserService.call();

    registrationNeeded(user);

    if (!CheckRoleUtil.hasRole(user, MarketConstants.KODEKONVEYOR_CONTRACT))
      throw new UnauthorizedException(
          RegisterConstants.UNAUTHORIZED_NOT_ENOUGH_RIGHTS
      );
    return null;
  }

  private void registrationNeeded(final UserEntity user) {

    if (!CheckRoleUtil.hasRole(user, MarketConstants.CAN_BE_PAID_ROLE))
      throw new UnauthorizedException(RegisterConstants.IN_ADD_TO_ROLE);

  }
}
