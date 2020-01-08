package com.kodekonveyor.market.lead;

import com.kodekonveyor.authentication.RoleEntity;
import com.kodekonveyor.authentication.UserEntity;

public class CheckRoleUtil {

  public static boolean hasRole(final UserEntity user, final String roleName) {
    return user.getRoles().stream()
        .anyMatch(role -> rolesNameEqual(role, roleName));
  }

  private static boolean
      rolesNameEqual(final RoleEntity role, final String roleName) {
    return role.getName().equals(roleName);
  }

}
