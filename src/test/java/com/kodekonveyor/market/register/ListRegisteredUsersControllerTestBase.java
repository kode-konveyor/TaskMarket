package com.kodekonveyor.market.register;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.kodekonveyor.authentication.AuthenticatedUserService;
import com.kodekonveyor.authentication.RoleEntityRepository;
import com.kodekonveyor.authentication.RoleEntityRepositoryStubs;
import com.kodekonveyor.authentication.UserEntityRepository;
import com.kodekonveyor.authentication.UserEntityRepositoryStubs;

public class ListRegisteredUsersControllerTestBase {

  @InjectMocks
  ListRegisteredUsersController listRegisteredUsersController;

  @Mock
  RoleEntityRepository roleEntityRepository;

  @Mock
  UserEntityRepository userEntityRepository;

  @Mock
  MarketUserEntityRepository marketUserEntityRepository;

  @Mock
  AuthenticatedUserService authenticatedUserService;

  @BeforeEach
  public void before() {
    RoleEntityRepositoryStubs.behaviour(roleEntityRepository);
    UserEntityRepositoryStubs.behaviour(userEntityRepository);
    MarketUserEntityRepositoryStubs.behaviour(marketUserEntityRepository);
  }

}
