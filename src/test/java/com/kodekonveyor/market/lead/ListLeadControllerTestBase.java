package com.kodekonveyor.market.lead;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;

import com.kodekonveyor.authentication.AuthenticatedUserService;
import com.kodekonveyor.market.project.ProjectEntityRepository;
import com.kodekonveyor.market.project.ProjectEntityRepositoryStubs;

public class ListLeadControllerTestBase {

  @InjectMocks
  ListLeadController listleadController;

  @Mock
  AuthenticatedUserService authenticatedUserService;
  @Mock
  LeadEntityRepository leadEntityRepository;
  @Mock
  ProjectEntityRepository projectEntityRepository;
  @Mock
  Logger loggerService;

  @BeforeEach
  void setUp() {
    LeadEntityStubs.behaviour(leadEntityRepository);
    ProjectEntityRepositoryStubs.behaviour(projectEntityRepository);
  }

}
