package com.kodekonveyor.market.project;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;

import com.kodekonveyor.authentication.AuthenticatedUserService;
import com.kodekonveyor.authentication.AuthenticatedUserServiceStubs;
import com.kodekonveyor.market.register.MarketUserEntityRepository;
import com.kodekonveyor.market.register.MarketUserEntityRepositoryStubs;

public class AddFundsToProjectControllerTestBase {

  @InjectMocks
  AddFundsToProjectController addFundsToProjectController;

  @Mock
  ProjectEntityRepository projectEntityRepository;

  @Mock
  MarketUserEntityRepository marketUserEntityRepository;

  @Mock
  AuthenticatedUserService authenticatedUserService;

  @Mock
  Logger logger;

  @BeforeEach
  void setUp() {
    AuthenticatedUserServiceStubs.authenticated(authenticatedUserService);
    ProjectEntityRepositoryStubs.behaviour2(projectEntityRepository);
    MarketUserEntityRepositoryStubs.behaviour(marketUserEntityRepository);
  }
}
