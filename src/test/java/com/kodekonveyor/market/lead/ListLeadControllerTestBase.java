package com.kodekonveyor.market.lead;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.kodekonveyor.authentication.AuthenticatedUserService;
import com.kodekonveyor.market.LoggerService;

public class ListLeadControllerTestBase {

  @Mock
  AuthenticatedUserService authenticatedUserService;
  @Mock
  LeadEntityRepository leadEntityRepository;
  @InjectMocks
  ListLeadController listleadController;

  @Mock
  LoggerService loggerService;

  @BeforeEach
  void setUp() {
    LeadEntityStubs.behaviour(leadEntityRepository);
  }

}
