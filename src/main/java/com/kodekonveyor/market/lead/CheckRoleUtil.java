package com.kodekonveyor.market.lead;

import com.kodekonveyor.authentication.RoleEntity;
import com.kodekonveyor.authentication.UserEntity;
import com.kodekonveyor.market.project.ProjectEntity;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Set;

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

    public static boolean hasAnyRole(
            final UserEntity user,
            final String... roleNames
    ) {
        final Set<RoleEntity> roles = user.getRole();
        return roles.stream()
                .anyMatch(role -> ArrayUtils.contains(roleNames, role.getName()));
    }

    private static boolean
      rolesNameEqual(
          final RoleEntity role, final ProjectEntity project,
          final String roleName
      ) {
    return project.getRole().contains(role) && role.getName().equals(roleName);
  }

}
