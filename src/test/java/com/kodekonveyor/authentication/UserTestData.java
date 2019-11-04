package com.kodekonveyor.authentication;

import java.util.ArrayList;
import java.util.List;

public class UserTestData {

  public final String AUTH0ID = "github|424242@kode-konveyor.eu.auth0.com/";
  public final String BAD_AUTH0ID = "github|424241@kode-konveyor.eu.auth0.com/";
  public final String LOGIN = "424242";
  public final String BAD_LOGIN = "424241";
  public final UserEntity USER = createUSER();
  public final UserEntity BAD_USER_BEFORE_SAVE = createBAD_USER_BEFORE_SAVE();
  public final UserEntity BAD_USER = createBAD_USER();
  public final List<UserEntity> USER_LIST = createUSER_LIST();
  public final long USER_ID = 4242;
  public final long BAD_USER_ID = 4241;
  public final List<Object> EMPTY_LIST = new ArrayList<>();
  public final String NO_AUTHENTICATION = "No Authentication";
  public final String NO_CREDENTIAL = "No Credential";

  private List<UserEntity> createUSER_LIST() {
    return List.of(USER);
  }

  private UserEntity createUSER() {
    final UserEntity userEntity = new UserEntity();
    userEntity.setAuth0id(AUTH0ID);
    userEntity.setLogin(LOGIN);
    userEntity.setId(USER_ID);
    return userEntity;
  }

  private UserEntity createBAD_USER() {
    final UserEntity userEntity = createBAD_USER_BEFORE_SAVE();
    userEntity.setId(BAD_USER_ID);
    return userEntity;
  }

  private UserEntity createBAD_USER_BEFORE_SAVE() {
    final UserEntity userEntity = new UserEntity();
    userEntity.setAuth0id(BAD_AUTH0ID);
    userEntity.setLogin(BAD_LOGIN);
    return userEntity;
  }

}
