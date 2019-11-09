package com.kodekonveyor.market.lead;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.kodekonveyor.authentication.AuthenticatedUserService;
import com.kodekonveyor.authentication.UserTestData;
import com.kodekonveyor.market.LoggerService;
import com.kodekonveyor.market.register.RegisterTestData;

public class ListLeadControllerTestBase {

  @InjectMocks
  ListLeadController listleadController;
  @Mock
  LeadEntityRepository leadEntityRepository;
  @Mock
  LoggerService loggerService;
  @Mock
  AuthenticatedUserService authenticatedUserService;

  LeadTestData leadTestData;
  UserTestData userTestData;

  @BeforeEach
  void setUp() {
    userTestData = new UserTestData();
    final RegisterTestData registerTestData =
        new RegisterTestData(userTestData);
    leadTestData = new LeadTestData(registerTestData);
    LeadEntityStubs.behaviour(leadEntityRepository, leadTestData);
  }

}
