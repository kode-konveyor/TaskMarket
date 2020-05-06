package com.kodekonveyor.market.lead;

import java.util.Set;

import com.kodekonveyor.authentication.RoleEntity;
import com.kodekonveyor.authentication.UserEntity;
import com.kodekonveyor.market.project.ProjectEntity;

public class CheckRoleUtil {

  public static boolean
      hasRole(
          final UserEntity user, final ProjectEntity project,
          final String roleName
      ) {
    final Set<RoleEntity> roles = user.getRole();
    return roles.stream()
        .anyMatch(role -> rolesNameEqual(role, project, roleName));
  }

  private static boolean
      rolesNameEqual(
          final RoleEntity role, final ProjectEntity project,
          final String roleName
      ) {
    return project.getRole().contains(role) && role.getName().equals(roleName);
  }

}
