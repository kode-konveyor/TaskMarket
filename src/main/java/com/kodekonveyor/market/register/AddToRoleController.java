package com.kodekonveyor.market.register;

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
public class AddToRoleController {

  @Autowired
  AuthenticatedUserService authenticatedUserService;

  @PutMapping(UrlMapConstants.ADD_TO_ROLE_PATH)
  public Object call(final String projectname, final String projectrole) {
    final UserEntity user = authenticatedUserService.call();
    if (!CheckRoleUtil.hasRole(user, MarketConstants.CAN_BE_PAID_ROLE))
      throw new UnauthorizedException(RegisterConstants.IN_ADD_TO_ROLE);
    return null;
  }

}
