package com.kodekonveyor.market.tasks;

import org.mockito.InjectMocks;
import org.mockito.Mock;

public class UpdateTasksServiceTestBase {

  @InjectMocks
  UpdateTasksService updateTasksService;

  @Mock
  TaskEntityRepository taskEntityRepository;

  @Mock
  UpdateGithubIssueService updateGithubIssueService;

}
