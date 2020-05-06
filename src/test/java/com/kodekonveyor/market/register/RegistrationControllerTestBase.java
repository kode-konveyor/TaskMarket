package com.kodekonveyor.market.register;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.kodekonveyor.authentication.AuthenticatedUserService;
import com.kodekonveyor.authentication.AuthenticatedUserServiceStubs;
import com.kodekonveyor.market.payment.LegalFormEntityRepository;
import com.kodekonveyor.market.payment.LegalFormEntityRepositoryStubs;

public class RegistrationControllerTestBase {

  @InjectMocks
  RegistrationController registrationController;

  @Mock
  AuthenticatedUserService authenticatedUserService;

  @Mock
  MarketUserEntityRepository marketUserEntityRepository;

  @Mock
  LegalFormEntityRepository legalFormEntityRepository;

  @BeforeEach
  void setUp() {
    AuthenticatedUserServiceStubs
        .authenticated(authenticatedUserService);
    MarketUserEntityRepositoryStubs.behaviour(marketUserEntityRepository);
    LegalFormEntityRepositoryStubs.behaviour(legalFormEntityRepository);
  }
}
