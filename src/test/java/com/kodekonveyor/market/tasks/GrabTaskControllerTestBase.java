package com.kodekonveyor.market.tasks;

import com.kodekonveyor.market.project.MilestoneEntityRepository;
import com.kodekonveyor.market.project.MilestoneEntityRepositoryStubs;
import com.kodekonveyor.market.project.ProjectEntityRepository;
import com.kodekonveyor.market.project.ProjectEntityRepositoryStubs;
import com.kodekonveyor.market.project.PullRequestEntityStubs;
import com.kodekonveyor.market.project.PullrequestEntityRepository;
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

  @Mock
  UpdateGithubIssueService updateGithubIssueService;

  @Mock
  MilestoneEntityRepository milestoneEntityRepository;

  @Mock
  ProjectEntityRepository projectEntityRepository;

  @Mock
  CheckUpforgrabTasksService checkUpforgrabTasksService;

  @Mock
  PullrequestEntityRepository pullrequestEntityRepository;

  @BeforeEach
  void setUp() {
    AuthenticatedUserServiceStubs.authenticated(authenticatedUserService);
    MarketUserEntityRepositoryStubs.behaviour(marketUserEntityRepository);
    TaskEntityRepositoryStubs.behaviour2(taskEntityRepository);
    MilestoneEntityRepositoryStubs.behaviour(milestoneEntityRepository);
    ProjectEntityRepositoryStubs.behaviour(projectEntityRepository);
    PullRequestEntityStubs.pullRequestIssued(pullrequestEntityRepository);
  }

}
