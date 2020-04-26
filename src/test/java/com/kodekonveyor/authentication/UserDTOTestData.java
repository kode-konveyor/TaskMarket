package com.kodekonveyor.authentication;

import java.util.Set;

import javax.annotation.Generated;

@Generated("by zenta-tools")
public class UserDTOTestData {

  public final static UserDTO get() {
    final UserDTO userDTO = new UserDTO();
    userDTO.setId(UserTestData.ID);
    userDTO.setRole(Set.of(RoleTestData.ID));
    userDTO.setLogin(UserTestData.LOGIN);

    return userDTO;
  };

}
