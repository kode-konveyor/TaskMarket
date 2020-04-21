package com.kodekonveyor.market.register;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.kodekonveyor.authentication.AuthenticatedUserService;

public class MarketUserCompilerServiceTestBase {

  @InjectMocks
  MarketUserCompilerService marketUserCompilerService;

  @Mock
  AuthenticatedUserService authenticatedUserService;
}
