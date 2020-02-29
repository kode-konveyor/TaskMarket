package com.kodekonveyor.market.project;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class CreateProjectControllerTestBase {

  @InjectMocks
  CreateProjectController createProjectController;

  @Mock
  ProjectEntityRepository projectEntityRepository;

  @BeforeEach
  void setUp() {
    ProjectEntityStubs.behaviour(projectEntityRepository);
  }

}
