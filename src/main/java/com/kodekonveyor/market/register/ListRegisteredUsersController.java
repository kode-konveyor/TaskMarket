package com.kodekonveyor.market.register;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodekonveyor.authentication.AuthenticatedUserService;
import com.kodekonveyor.authentication.RoleConstants;
import com.kodekonveyor.authentication.RoleEntity;
import com.kodekonveyor.authentication.UserEntity;
import com.kodekonveyor.authentication.UserEntityRepository;
import com.kodekonveyor.market.UrlMapConstants;

@RestController
public class ListRegisteredUsersController {

  @Autowired
  MarketUserCompilerService marketUserCompilerService;

  @Autowired
  AuthenticatedUserService authenticatedUserService;

  @Autowired
  UserEntityRepository userEntityRepository;

  @PutMapping(UrlMapConstants.LIST_REGISTERED_USERS_PATH)
  public List<MarketUserEntity> call() {
    final UserEntity userEntity = authenticatedUserService.call();
    final Set<RoleEntity> roleEntities = userEntity.getRole();

    for (final RoleEntity role : roleEntities)
      if (role.getName().equals(RoleConstants.ROLE_CONTRACT)) {
        final List<UserEntity> usersList =
            userEntityRepository.findByRole(role);
        for (final UserEntity otherUser : usersList) {
          final Object marketUserDTO =
              marketUserCompilerService.call(otherUser.getId());
          System.out.println("");
        }
      } else if (role.getName().equals(RoleConstants.ROLE_CONTRACT)) {

      }

    return null;

  }

}
