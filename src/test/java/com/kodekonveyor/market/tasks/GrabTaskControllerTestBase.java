package com.kodekonveyor.market.tasks;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.kodekonveyor.authentication.AuthenticatedUserService;
import com.kodekonveyor.authentication.AuthenticatedUserServiceStubs;
import com.kodekonveyor.market.register.MarketUserEntityRepository;
import com.kodekonveyor.market.register.MarketUserEntityRepositoryStubs;

public class GrabTaskControllerTestBase {

  @InjectMocks
  GrabTaskController grabTaskController;

  @Mock
  TaskEntityRepository taskEntityRepository;

  @Mock
  AuthenticatedUserService authenticatedUserService;

  @Mock
  MarketUserEntityRepository marketUserEntityRepository;

  @BeforeEach
  void setUp() {
    AuthenticatedUserServiceStubs.authenticated(authenticatedUserService);
    MarketUserEntityRepositoryStubs.behaviour(marketUserEntityRepository);
    //TaskEntityRepositoryStubs.behaviour(taskEntityRepository);
  }
}
