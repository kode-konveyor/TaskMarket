package com.kodekonveyor.authentication;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class AuthenticatedUserServiceTestBase {

  @InjectMocks
  AuthenticatedUserService authenticatedUserService;
  @Mock
  UserEntityRepository userEntityRepository;
  UserTestData userTestData;

  @BeforeEach
  public void setUp() {
    userTestData = new UserTestData();
    UserEntityRepositoryStubs.behaviour(userEntityRepository, userTestData);
  }

}
