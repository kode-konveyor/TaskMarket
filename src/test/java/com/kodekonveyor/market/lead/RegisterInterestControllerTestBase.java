package com.kodekonveyor.market.lead;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.kodekonveyor.market.LoggerService;

public class RegisterInterestControllerTestBase {

  @Mock
  LeadEntityRepository leadEntityRepository;

  @Mock
  LoggerService loggerService;

  @InjectMocks
  RegisterInterestController registerInterestController;

  @BeforeEach
  void setUp() {
    LeadEntityStubs.behaviour(leadEntityRepository);
  }

}
