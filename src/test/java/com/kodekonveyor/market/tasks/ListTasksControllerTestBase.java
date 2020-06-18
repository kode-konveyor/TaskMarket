package com.kodekonveyor.market.tasks;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.kodekonveyor.authentication.AuthenticatedUserService;
import com.kodekonveyor.market.project.ProjectEntityRepository;
import com.kodekonveyor.market.register.MarketUserEntityRepository;

public class ListTasksControllerTestBase {

  @InjectMocks
  ListTasksController listTasksController;

  @Mock
  AuthenticatedUserService authenticatedUserService;

  @Mock
  MarketUserEntityRepository marketUserEntityRepository;

  @Mock
  TaskEntityRepository taskEntityRepository;

  @Mock
  ProjectEntityRepository projectEntityRepository;

}
