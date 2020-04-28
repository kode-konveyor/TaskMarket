package com.kodekonveyor.market.register;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.kodekonveyor.authentication.AuthenticatedUserService;
import com.kodekonveyor.authentication.RoleEntityRepository;
import com.kodekonveyor.authentication.RoleEntityRepositoryStubs;
import com.kodekonveyor.authentication.UserEntityRepository;
import com.kodekonveyor.authentication.UserEntityRepositoryStubs;
import com.kodekonveyor.market.project.ProjectEntityRepository;
import com.kodekonveyor.market.project.ProjectEntityRepositoryStubs;

public class AddToRoleControllerTestBase {

  @InjectMocks
  AddToRoleController addToRoleController;

  @Mock
  AuthenticatedUserService authenticatedUserService;

  @Mock
  UserEntityRepository userEntityRepository;

  @Mock
  RoleEntityRepository roleEntityRepository;

  @Mock
  ProjectEntityRepository projectEntityRepository;

  @BeforeEach
  void setUp() {
    UserEntityRepositoryStubs.behaviour(userEntityRepository);
    RoleEntityRepositoryStubs.behaviour(roleEntityRepository);
    ProjectEntityRepositoryStubs.behaviour(projectEntityRepository);
  }
}
