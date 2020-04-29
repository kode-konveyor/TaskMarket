package com.kodekonveyor.market.tasks;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.kodekonveyor.authentication.AuthenticatedUserService;
import com.kodekonveyor.market.project.ProjectEntityRepository;
import com.kodekonveyor.market.project.ProjectEntityRepositoryStubs;

public class UpdateTasksControllerTestBase {

  @InjectMocks
  UpdateTasksController updateTasksController;

  @Mock
  AuthenticatedUserService authenticatedUserService;

  @Mock
  ProjectEntityRepository projectEntityRepository;

  @Mock
  GetRepositoryTasksService getRepositoryTasksService;

  @Mock
  GithubCallService githubRequest;

  @Mock
  MarketUserEntityRepository marketRepository;

  @Mock
  TaskEntityRepository taskEntityRepository;

  @InjectMocks
  UpdateTasksController updateTasksController;

  @Mock
  UserEntityRepository userRepository;

  @BeforeEach
  void setUp() throws JSONException {
    AuthenticatedUserStubs.kodekonveyorContract(authenticatedUserService);
    ProjectEntityRepositoryStubs.behaviour(projectEntityRepository);
    UserEntityRepositoryStubs.behaviour(userRepository);
    MarketUserStubs.behaviour(marketRepository, registerTestData);
    GetRepositoryTasksServiceStubs.behaviour(getRepositoryTasksService);

  }

}
