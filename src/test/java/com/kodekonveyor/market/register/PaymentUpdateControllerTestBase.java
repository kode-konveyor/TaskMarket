
package com.kodekonveyor.market.register;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.kodekonveyor.authentication.AuthenticatedUserService;

public class PaymentUpdateControllerTestBase {

  @Mock
  AuthenticatedUserService authenticatedUserService;

  @Mock
  MarketUserEntityRepository marketUserEntityRepository;

  @InjectMocks
  PaymentUpdateController paymentUpdateController;

  MarketUserDTOTestData registerTestData;

  @BeforeEach
  void setUp() {
    final MarketUserDTOTestData registerTestData = new MarketUserDTOTestData();

    MarketUserStubs
        .behaviour(marketUserEntityRepository, registerTestData);
  }
}
