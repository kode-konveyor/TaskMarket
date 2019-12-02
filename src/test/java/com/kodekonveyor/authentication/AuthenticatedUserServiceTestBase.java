package com.kodekonveyor.authentication;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.kodekonveyor.market.LoggerService;

public class AuthenticatedUserServiceTestBase {

  @InjectMocks
  AuthenticatedUserService authenticatedUserService;
  @Mock
  LoggerService loggerService;

  @Mock
  UserEntityRepository userEntityRepository;

  @BeforeEach
  public void setUp() {
    UserEntityRepositoryStubs.behaviour(userEntityRepository);
  }

}
