package com.kodekonveyor.authentication;

public class RoleUtil {

  public static final RoleEntity getNameRegistered() {
    final RoleEntity role = new RoleEntity();
    role.setName(RoleConstants.ROLE_REGISTERED);
    return role;
  }

}
