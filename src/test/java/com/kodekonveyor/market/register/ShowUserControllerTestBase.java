package com.kodekonveyor.market.register;

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
  Logger logger;

  @BeforeEach
  void setUp() {
    MarketUserEntityRepositoryStubs
        .behaviour(marketUserEntityRepository);
  }

}
