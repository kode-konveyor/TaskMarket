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

  @BeforeEach
  void setUp() {
    ProjectEntityRepositoryStubs.behaviour(projectEntityRepository);
  }
}
