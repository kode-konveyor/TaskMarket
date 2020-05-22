package com.kodekonveyor.market.tasks;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;

import com.kodekonveyor.authentication.UserEntityRepository;
import com.kodekonveyor.market.project.MilestoneEntityRepository;
import com.kodekonveyor.market.project.ProjectEntityRepository;
import com.kodekonveyor.market.register.MarketUserEntityRepository;

public class GetRepositoryTasksServiceTestBase {

  @InjectMocks
  GetRepositoryTasksService getRepositoryTasksService;

  @Mock
  GithubCallService githubRequest;

  @Mock
  MarketUserEntityRepository marketUserEntityRepository;

  @Mock
  UserEntityRepository userEntityRepository;

  @Mock
  ProjectEntityRepository projectEntityRepository;

  @Mock
  TaskEntityRepository taskEntityRepository;

  @Mock
  MilestoneEntityRepository milestoneEntityRepository;

  @Mock
  Logger loggerService;

  @BeforeEach
  void setUp() {
    GithubRequestStubs.behaviour(githubRequest);
  }

}
