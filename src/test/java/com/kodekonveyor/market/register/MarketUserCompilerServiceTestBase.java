package com.kodekonveyor.market.register;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.kodekonveyor.authentication.AuthenticatedUserService;
import com.kodekonveyor.market.project.ProjectEntityRepository;
import com.kodekonveyor.market.project.ProjectEntityRepositoryStubs;

public class MarketUserCompilerServiceTestBase {

  @InjectMocks
  MarketUserCompilerService marketUserCompilerService;

  @Mock
  AuthenticatedUserService authenticatedUserService;
  @Mock
  private ProjectEntityRepository projectEntityRepository;
  @Mock
  private MarketUserEntityRepository marketUserEntityRepository;

  @BeforeEach
  void setUp() {
    ProjectEntityRepositoryStubs.behaviour(projectEntityRepository);
    MarketUserEntityRepositoryStubs.behaviour(marketUserEntityRepository);
  }
}
