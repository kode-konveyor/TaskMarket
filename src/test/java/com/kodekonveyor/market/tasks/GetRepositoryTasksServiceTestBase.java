package com.kodekonveyor.market.tasks;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class GetRepositoryTasksServiceTestBase {

  @InjectMocks
  GetRepositoryTasksService getRepositoryTasksService;

  @Mock
  GithubCallService githubRequest;

  @BeforeEach
  void setUp() {
    GithubRequestStubs.behaviour(githubRequest);
  }

}
