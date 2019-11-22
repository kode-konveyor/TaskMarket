package com.kodekonveyor.authentication;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class UserTestData {

  public final UserDTO USER_DTO;
  public final String GITHUB_ID = "gituser";
  public final Long USER_WITH_NO_MARKET_USER_ID = (long) 44;
  public final String USER_WITH_NO_MARKET_USER_ID_LOGIN = "userNoMarketUser";

  public final UserEntity TEST_USER_ENTITY;
  public final RoleEntity TEST_ROLE_ENTITY;
  public final String TEST_ROLE = "test_testrole";
  public final String BAD_LOGIN = "badlogin";
  public final String LOGIN = "gituser";
  public final List<UserEntity> USER_LIST;
  public final List<UserEntity> EMPTY_LIST = new ArrayList<>();
  public final long BAD_USER_ID = -42;
  public final UserEntity BAD_USER_BEFORE_SAVE;
  public final Long USER_ID = (long) 42;
  public final String NO_AUTHENTICATION = "No Authentication";
  public final String NO_CREDENTIAL = "No Credential";
  public final UserEntity SALES_USER;
  public String SHOULD_NOT_HAPPEN = "This should not happen";
  public final String KODEKONVEYOR_SALES_ROLE = "kodekonveyor_sales";
  public final RoleEntity SALES_ROLE;
  public final UserEntity TEST_USER_ENTITY_NO_MARKET_USER;

  public UserTestData() {
    TEST_ROLE_ENTITY = createTEST_ROLE_ENTITY();
    TEST_USER_ENTITY =
        createTEST_USER_ENTITY();
    TEST_USER_ENTITY_NO_MARKET_USER = createTEST_USER_ENTITY_NO_MARKET_USER();
    SALES_ROLE = createSALES_ROLE();
    SALES_USER = createSALES_USER();
    USER_LIST = List.of(TEST_USER_ENTITY);
    USER_DTO = createUSER_DTO();
    BAD_USER_BEFORE_SAVE = createBAD_USER_BEFORE_SAVE();

  }

  private RoleEntity createSALES_ROLE() {
    final RoleEntity role = new RoleEntity();
    role.setName(KODEKONVEYOR_SALES_ROLE);
    return role;
  }

  private UserEntity createSALES_USER() {
    final UserEntity userEntity = createTEST_USER_ENTITY();
    userEntity.setRoles(Set.of(SALES_ROLE));
    return userEntity;
  }

  private UserEntity createBAD_USER_BEFORE_SAVE() {
    final UserEntity userEntity = new UserEntity();
    userEntity.setLogin(LOGIN);
    return userEntity;
  }

  private UserDTO createUSER_DTO() {
    final UserDTO user = new UserDTO();
    user.setId(USER_ID);
    user.setLogin(LOGIN);
    return user;
  }

  private UserEntity createTEST_USER_ENTITY_NO_MARKET_USER() {
    final UserEntity user = new UserEntity();
    user.setId(USER_WITH_NO_MARKET_USER_ID);
    user.setLogin(USER_WITH_NO_MARKET_USER_ID_LOGIN);
    return user;
  }

  private UserEntity createTEST_USER_ENTITY() {
    final UserEntity user = new UserEntity();
    user.setLogin(GITHUB_ID);
    user.setRoles(Set.of(TEST_ROLE_ENTITY));
    user.setId(USER_ID);
    return user;
  }

  private RoleEntity createTEST_ROLE_ENTITY() {
    final RoleEntity role = new RoleEntity();
    role.setName(TEST_ROLE);
    return role;
  }
}
