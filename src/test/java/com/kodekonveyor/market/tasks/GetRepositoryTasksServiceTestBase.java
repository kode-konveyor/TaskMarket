package com.kodekonveyor.market.tasks;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.kodekonveyor.technical.GithubGetService;

public class GetRepositoryTasksServiceTestBase {

  @InjectMocks
  GetRepositoryTasksService getRepositoryTasksService;

  @Mock
  GithubGetService githubRequest;

  @BeforeEach
  void setUp() {
    GithubRequestStubs.behaviour(githubRequest);
  }

}
