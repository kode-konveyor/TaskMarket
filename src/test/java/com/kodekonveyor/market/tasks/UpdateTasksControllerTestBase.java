package com.kodekonveyor.market.tasks;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.kodekonveyor.authentication.AuthenticatedUserService;
import com.kodekonveyor.market.LoggerService;

public class UpdateTasksControllerTestBase {

  @Mock
  AuthenticatedUserService authenticatedUserService;

  @Mock
  LoggerService loggerService;

  @InjectMocks
  UpdateTasksController updateTasksController;
}
