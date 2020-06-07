package com.kodekonveyor.market.tasks;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.kodekonveyor.market.project.PullrequestEntityRepository;
import com.kodekonveyor.market.technical.GithubGraphqlService;

public class UngrabServiceTestBase {

  @InjectMocks
  UngrabService ungrabService;

  @Mock
  TaskEntityRepository taskEntityRepository;

  @Mock
  PullrequestEntityRepository pullrequestEntityRepository;

  @Mock
  GithubGraphqlService githubGraphqlService;

}
