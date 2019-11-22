package com.kodekonveyor.authentication;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.kodekonveyor.market.LogTestData;
import com.kodekonveyor.market.LoggerService;

public class AuthenticatedUserServiceTestBase {

  @InjectMocks
  AuthenticatedUserService authenticatedUserService;
  @Mock
  UserEntityRepository userEntityRepository;
  @Mock
  LoggerService loggerService;

  LogTestData logTestData;

  UserTestData userTestData;

  @BeforeEach
  public void setUp() {
    userTestData = new UserTestData();
    logTestData = new LogTestData();
    UserEntityRepositoryStubs.behaviour(userEntityRepository, userTestData);
  }

}
