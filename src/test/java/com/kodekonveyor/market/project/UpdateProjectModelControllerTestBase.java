package com.kodekonveyor.market.project;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;

import com.kodekonveyor.authentication.AuthenticatedUserService;
import com.kodekonveyor.authentication.AuthenticatedUserServiceStubs;

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

  @BeforeEach
  void setUp() {
    ProjectEntityRepositoryStubs.behaviour(projectEntityRepository);
    MilestoneEntityRepositoryStubs.behaviour(milestoneEntityRepository);
    AuthenticatedUserServiceStubs.projectManager(authenticatedUserService);
  }
}
