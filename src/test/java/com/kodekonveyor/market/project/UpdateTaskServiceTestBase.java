package com.kodekonveyor.market.project;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.kodekonveyor.authentication.AuthenticatedUserService;
import com.kodekonveyor.market.register.MarketUserEntityRepository;
import com.kodekonveyor.market.tasks.TaskEntityRepository;
import com.kodekonveyor.market.tasks.TaskEntityStubs;
import com.kodekonveyor.market.tasks.UpdateTasksService;

public class UpdateTaskServiceTestBase {

  @InjectMocks
  UpdateTasksService updateTasksService;

  @Mock
  ProjectEntityRepository projectEntityRepository;

  @Mock
  TaskEntityRepository taskEntityRepository;

  @Mock
  AuthenticatedUserService authenticatedUserService;

  @Mock
  MarketUserEntityRepository marketUserEntityRepository;

  @BeforeEach
  void setUp() {
    TaskEntityStubs.behaviour(taskEntityRepository, marketUserEntityRepository);
    ProjectEntityRepositoryStubs.behaviour(projectEntityRepository);
  }
}
