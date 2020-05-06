package com.kodekonveyor.market.project;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class UpdateProjectModelControllerTestBase {

  @InjectMocks
  UpdateProjectModelController updateProjectModelController;

  @Mock
  ProjectEntityRepository projectEntityRepository;

  @Mock
  MilestoneEntityRepository milestoneEntityRepository;

  @BeforeEach
  void setUp() {
    ProjectEntityRepositoryStubs.behaviour(projectEntityRepository);
    MilestoneEntityRepositoryStubs.behaviour(milestoneEntityRepository);
  }
}
