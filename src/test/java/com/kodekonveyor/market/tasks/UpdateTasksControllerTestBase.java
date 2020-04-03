package com.kodekonveyor.market.tasks;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;

import com.kodekonveyor.authentication.AuthenticatedUserService;

public class UpdateTasksControllerTestBase {

  @Mock
  AuthenticatedUserService authenticatedUserService;

  @Mock
  Logger loggerService;

  @InjectMocks
  UpdateTasksController updateTasksController;
}
