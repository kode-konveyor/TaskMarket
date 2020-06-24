package com.kodekonveyor.market.register;

import com.kodekonveyor.authentication.UserEntityRepository;
import com.kodekonveyor.authentication.UserEntityRepositoryStubs;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;

import com.kodekonveyor.authentication.AuthenticatedUserService;

public class ShowUserControllerTestBase {

  @InjectMocks
  ShowUserController showUserController;

  @Mock
  AuthenticatedUserService authenticatedUserService;

  @Mock
  MarketUserEntityRepository marketUserEntityRepository;

  @Mock
  UserEntityRepository userEntityRepository;

  @Mock
  Logger logger;

  @BeforeEach
  void setUp() {
    MarketUserEntityRepositoryStubs
        .behaviour(marketUserEntityRepository);

    UserEntityRepositoryStubs.behaviour(userEntityRepository);
  }

}
