package com.kodekonveyor.authentication;

public class UserDTOTestData {

  public static final UserDTO get() {
    final UserDTO user = new UserDTO();
    user.setId(UserEntityTestData.ID);
    user.setLogin(UserEntityTestData.LOGIN);
    return user;
  }

}
