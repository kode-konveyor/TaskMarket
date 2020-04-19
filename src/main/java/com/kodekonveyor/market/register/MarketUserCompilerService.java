package com.kodekonveyor.market.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodekonveyor.authentication.AuthenticatedUserService;
import com.kodekonveyor.authentication.UserEntity;
import com.kodekonveyor.market.MarketConstants;
import com.kodekonveyor.market.UnauthorizedException;
import com.kodekonveyor.market.lead.CheckRoleUtil;

@Service
public class MarketUserCompilerService {

  @Autowired
  private AuthenticatedUserService authenticatedUserService;

  public Object call(final Long userId) {
    final UserEntity user = authenticatedUserService.call();
    checkRole(user);
    return null;
  }

  private void checkRole(final UserEntity user) {
    if (!CheckRoleUtil.hasRole(user, MarketConstants.REGISTERED_ROLE))
      throw new UnauthorizedException(
          RegisterConstants.UNAUTHORIZED_NOT_ENOUGH_RIGHTS
      );
  }

}
