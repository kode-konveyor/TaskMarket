package com.kodekonveyor.market.lead;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.kodekonveyor.authentication.UserTestData;
import com.kodekonveyor.market.LogTestData;
import com.kodekonveyor.market.LoggerService;
import com.kodekonveyor.market.register.RegisterTestData;

public class RegisterInterestControllerTestBase {

  @InjectMocks
  RegisterInterestController registerInterestController;
  @Mock
  LeadEntityRepository leadEntityRepository;

  @Mock
  LoggerService loggerService;

  LeadTestData leadTestData;
  LogTestData logTestData;

  @BeforeEach
  void setUp() {
    final UserTestData userTestData = new UserTestData();
    logTestData = new LogTestData();
    final RegisterTestData registerTestData =
        new RegisterTestData(userTestData);
    leadTestData = new LeadTestData(registerTestData);
    LeadEntityStubs.behaviour(leadEntityRepository, leadTestData);
  }

}
