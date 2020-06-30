package com.kodekonveyor.market.project;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;

import com.kodekonveyor.authentication.AuthenticatedUserService;
import com.kodekonveyor.authentication.AuthenticatedUserServiceStubs;
import com.kodekonveyor.market.tasks.GetRepositoryTasksService;
import com.kodekonveyor.market.tasks.TaskEntityRepository;
import com.kodekonveyor.market.tasks.UpdateTasksService;

public class UpdateProjectModelControllerTestBase {

  @InjectMocks
  UpdateProjectModelController updateProjectModelController;

  @Mock
  ProjectEntityRepository projectEntityRepository;

  @Mock
  MilestoneEntityRepository milestoneEntityRepository;

  @Mock
  Logger logger;

  @Mock
  AuthenticatedUserService authenticatedUserService;

  @Mock
  GetRepositoryTasksService getRepositoryTasksService;

  @Mock
  UpdateTasksService updateTasksService;

  @Mock
  TaskEntityRepository taskEntityRepository;

  @BeforeEach
  void setUp() {
    ProjectEntityRepositoryStubs.behaviour(projectEntityRepository);
    MilestoneEntityRepositoryStubs.behaviour(milestoneEntityRepository);
    AuthenticatedUserServiceStubs.projectManager(authenticatedUserService);
  }
}
