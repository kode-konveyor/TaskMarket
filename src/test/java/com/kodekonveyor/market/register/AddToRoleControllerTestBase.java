package com.kodekonveyor.market.register;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.kodekonveyor.authentication.AuthenticatedUserService;

public class AddToRoleControllerTestBase {

  @InjectMocks
  AddToRoleController addToRoleController;

  @Mock
  AuthenticatedUserService authenticatedUserService;
}
