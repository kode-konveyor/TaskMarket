package com.kodekonveyor.market.lead;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;

import com.kodekonveyor.authentication.AuthenticatedUserService;

public class ListLeadControllerTestBase {

  @Mock
  AuthenticatedUserService authenticatedUserService;
  @Mock
  LeadEntityRepository leadEntityRepository;
  @InjectMocks
  ListLeadController listleadController;

  @Mock
  Logger loggerService;

  @BeforeEach
  void setUp() {
    LeadEntityStubs.behaviour(leadEntityRepository);
  }

}
