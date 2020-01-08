package com.kodekonveyor.market.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.kodekonveyor.authentication.AuthenticatedUserService;
import com.kodekonveyor.authentication.UserEntity;
import com.kodekonveyor.market.MarketConstants;
import com.kodekonveyor.market.UnauthorizedException;
import com.kodekonveyor.market.lead.CheckRoleUtil;

@Controller
public class PaymentUpdateController {

  private static final String IN_PAYMENT_UPDATE = "in payment update";

  @Autowired
  AuthenticatedUserService authenticatedUserService;

  public Object call(final String paymentDetails) {
    final UserEntity user = authenticatedUserService.call();
    if (!CheckRoleUtil.hasRole(user, MarketConstants.CAN_BE_PAID_ROLE))
      throw new UnauthorizedException(IN_PAYMENT_UPDATE);
    return null;
  }

}
