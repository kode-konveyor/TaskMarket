package com.kodekonveyor.market.project;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;

import com.kodekonveyor.authentication.AuthenticatedUserService;

public class CreateProjectControllerTestBase {

  @Mock
  AuthenticatedUserService authenticatedUserService;

  @InjectMocks
  CreateProjectController createProjectController;

  @Mock
  Logger loggerService;

  @Mock
  ProjectEntityRepository projectEntityRepository;

  @BeforeEach
  void setUp() {
    ProjectEntityStubs.behaviour(projectEntityRepository);
  }

}
