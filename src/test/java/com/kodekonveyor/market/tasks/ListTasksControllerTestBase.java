package com.kodekonveyor.market.tasks;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.kodekonveyor.authentication.AuthenticatedUserService;
import com.kodekonveyor.market.register.MarketUserEntityRepository;

public class ListTasksControllerTestBase {

  @Mock
  AuthenticatedUserService authenticatedUserService;

  @InjectMocks
  ListTasksController listTasksController;

  @Mock
  MarketUserEntityRepository marketUserEntityRepository;

  @Mock
  TaskEntityRepository taskRepository;

  @BeforeEach
  void setUp() {
    TaskEntityStubs.behaviour(taskRepository);
  }
}
