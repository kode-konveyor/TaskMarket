package com.kodekonveyor.market.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodekonveyor.authentication.AuthenticatedUserService;
import com.kodekonveyor.authentication.UserEntity;

@RestController
public class ShowUserController {

  @Autowired
  private AuthenticatedUserService authenticatedUserService;

  @GetMapping("/member/user")
  public MarketUserDTO call() {
    final UserEntity userEntity = authenticatedUserService.call();
    final MarketUserDTO marketUserDTO = new MarketUserDTO();
    marketUserDTO.setAuth0id(userEntity.getAuth0id());
    marketUserDTO.setLogin(userEntity.getLogin());
    return marketUserDTO;
  }

}
