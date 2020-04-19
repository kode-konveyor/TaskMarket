package com.kodekonveyor.market.tasks;

import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.kodekonveyor.authentication.AuthenticatedUserService;
import com.kodekonveyor.authentication.AuthenticatedUserStubs;
import com.kodekonveyor.authentication.UserEntityRepository;
import com.kodekonveyor.authentication.UserEntityRepositoryStubs;
import com.kodekonveyor.market.project.ProjectEntityRepository;
import com.kodekonveyor.market.project.ProjectEntityStubs;
import com.kodekonveyor.market.register.MarketUserDTOTestData;
import com.kodekonveyor.market.register.MarketUserEntityRepository;
import com.kodekonveyor.market.register.MarketUserStubs;

public class UpdateTasksControllerTestBase {

  @Mock
  AuthenticatedUserService authenticatedUserService;

  @Mock
  GetRepositoryTasksService getRepositoryTasksService;

  @Mock
  GithubCallService githubRequest;

  @Mock
  MarketUserEntityRepository marketRepository;

  @Mock
  ProjectEntityRepository projectEntityRepository;

  MarketUserDTOTestData registerTestData;

  @Mock
  TaskEntityRepository taskEntityRepository;

  @InjectMocks
  UpdateTasksController updateTasksController;
  @Mock
  UserEntityRepository userRepository;

  @BeforeEach
  void setUp() throws JSONException {
    AuthenticatedUserStubs.kodekonveyorContract(authenticatedUserService);
    ProjectEntityStubs.behaviour(projectEntityRepository);
    UserEntityRepositoryStubs.behaviour(userRepository);
    MarketUserStubs.behaviour(marketRepository, registerTestData);
    GetRepositoryTasksServiceStubs.behaviour(getRepositoryTasksService);

  }

}
