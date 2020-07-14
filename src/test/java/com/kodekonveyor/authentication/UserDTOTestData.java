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

  public static UserDTO getNotInDatabase() {
    final UserDTO userDTO = get();
    userDTO.setId(UserTestData.ID_NO_MARKET_USER);
    userDTO.setLogin(UserTestData.LOGIN_NO_MARKET_USER);
    return userDTO;
  }

  public static UserDTO getRegistered() {
    final UserDTO userDTO = get();
    userDTO.setId(UserTestData.ID_REGISTERED);
    return userDTO;
  }

}
