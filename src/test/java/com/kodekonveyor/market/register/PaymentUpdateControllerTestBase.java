
package com.kodekonveyor.market.register;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.kodekonveyor.authentication.AuthenticatedUserService;
import com.kodekonveyor.market.payment.PaymentUpdateController;
import com.kodekonveyor.market.payment.TransferTypeEntityRepository;
import com.kodekonveyor.market.payment.TransferTypeEntityRepositoryStubs;
import com.kodekonveyor.market.project.ProjectEntityRepository;
import com.kodekonveyor.market.project.ProjectEntityRepositoryStubs;

public class PaymentUpdateControllerTestBase {

  @InjectMocks
  PaymentUpdateController paymentUpdateController;

  @Mock
  AuthenticatedUserService authenticatedUserService;

  @Mock
  MarketUserEntityRepository marketUserEntityRepository;

  @Mock
  ProjectEntityRepository projectEntityRepository;

  @Mock
  TransferTypeEntityRepository transferTypeEntityRepository;

  @BeforeEach
  void setUp() {
    MarketUserEntityRepositoryStubs.behaviour(marketUserEntityRepository);
    ProjectEntityRepositoryStubs.behaviour(projectEntityRepository);
    TransferTypeEntityRepositoryStubs.behaviour(transferTypeEntityRepository);
  }
}
