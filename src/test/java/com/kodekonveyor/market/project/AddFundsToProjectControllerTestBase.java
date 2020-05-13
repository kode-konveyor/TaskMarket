package com.kodekonveyor.market.project;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class AddFundsToProjectControllerTestBase {

  @InjectMocks
  AddFundsToProjectController addFundsToProjectController;

  @Mock
  ProjectEntityRepository projectEntityRepository;

  @BeforeEach
  void setUp() {
    ProjectEntityRepositoryStubs.behaviour2(projectEntityRepository);
  }
}
