package com.kodekonveyor.authentication;

public class RoleEntityTestData {

  public static final String ROLE = "test_testrole";
  public static final String ROLE_SALES = "kodekonveyor_sales";

  public static final RoleEntity get() {
    final RoleEntity role = new RoleEntity();
    role.setName(ROLE);
    return role;
  }

  public static final RoleEntity getNameSales() {
    final RoleEntity role = new RoleEntity();
    role.setName(ROLE_SALES);
    return role;
  }
}
