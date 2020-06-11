package com.kodekonveyor.market.project;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;

import com.kodekonveyor.authentication.AuthenticatedUserService;
import com.kodekonveyor.authentication.RoleEntityRepository;
import com.kodekonveyor.authentication.RoleEntityRepositoryStubs;
import com.kodekonveyor.market.register.MarketUserEntityRepository;

public class CreateProjectControllerTestBase {

  @Mock
  AuthenticatedUserService authenticatedUserService;

  @InjectMocks
  CreateProjectController createProjectController;

  @Mock
  Logger loggerService;

  @Mock
  ProjectEntityRepository projectEntityRepository;

  @Mock
  RoleEntityRepository roleEntityRepository;

  @Mock
  MilestoneEntityRepository milestoneEntityRepository;

  @Mock
  PullrequestEntityRepository pullrequestEntityRepository;

  @Mock
  MarketUserEntityRepository marketUserEntityRepository;

  @BeforeEach
  void setUp() {
    ProjectEntityRepositoryStubs.behaviour(projectEntityRepository);
    MilestoneEntityRepositoryStubs.behaviour(milestoneEntityRepository);
    PullRequestEntityStubs.behaviour(pullrequestEntityRepository);
    RoleEntityRepositoryStubs.behaviour(roleEntityRepository);
  }

}
