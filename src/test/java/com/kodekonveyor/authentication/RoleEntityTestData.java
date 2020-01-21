package com.kodekonveyor.authentication;

public class RoleEntityTestData {

  public static final String ROLE = "test_testrole";
  public static final String ROLE_CAN_BE_PAYED = "can_be_payed";
  private static final String ROLE_MANAGER = "kode-konveyor/example_manager";
  public static final String ROLE_PROJECT = "kode-konveyor/example_coder";
  public static final String ROLE_REGISTERED = "registered";
  public static final String ROLE_SALES = "kodekonveyor_sales";

  public static final RoleEntity get() {
    final RoleEntity role = new RoleEntity();
    role.setName(ROLE);
    return role;
  }

  public static RoleEntity getNameCanBePayed() {
    final RoleEntity role = get();
    role.setName(ROLE_CAN_BE_PAYED);
    return role;
  }

  public static RoleEntity getNameManager() {
    final RoleEntity role = get();
    role.setName(ROLE_MANAGER);
    return role;
  }

  public static final RoleEntity getNameProject() {
    final RoleEntity role = get();
    role.setName(ROLE_PROJECT);
    return role;
  }

  public static final RoleEntity getNameRegistered() {
    final RoleEntity role = get();
    role.setName(ROLE_REGISTERED);
    return role;
  }

  public static final RoleEntity getNameSales() {
    final RoleEntity role = get();
    role.setName(ROLE_SALES);
    return role;
  }
}
