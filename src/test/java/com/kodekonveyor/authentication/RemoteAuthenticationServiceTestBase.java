package com.kodekonveyor.authentication;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.springframework.security.core.Authentication;

public class RemoteAuthenticationServiceTestBase {

  Logger loggerService = Mockito.mock(Logger.class);

  @Captor
  ArgumentCaptor<Authentication> newAuthentication;

  ServletResponse res = Mockito.mock(ServletResponse.class);

  ServletRequest req = Mockito.mock(ServletRequest.class);

  FilterChain filterChain = Mockito.mock(FilterChain.class);

  UserEntityRepository userRepository =
      Mockito.mock(UserEntityRepository.class);

  RemoteAuthenticationService remoteAuthenticationService =
      new RemoteAuthenticationService(userRepository, loggerService);

  @BeforeEach
  public void setUp() {
    UserEntityRepositoryStubs.behaviour(userRepository);
  }

}
