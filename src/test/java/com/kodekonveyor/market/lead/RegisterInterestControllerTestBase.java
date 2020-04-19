package com.kodekonveyor.market.lead;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;

public class RegisterInterestControllerTestBase {

  @Mock
  LeadEntityRepository leadEntityRepository;

  @Mock
  Logger loggerService;

  @InjectMocks
  RegisterInterestController registerInterestController;

  @BeforeEach
  void setUp() {
    LeadEntityStubs.behaviour(leadEntityRepository);
  }

}
