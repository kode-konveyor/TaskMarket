package com.kodekonveyor.authentication;

import javax.servlet.FilterChain;
import javax.servlet.ServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.Authentication;

import com.kodekonveyor.market.LoggerService;
import com.kodekonveyor.market.SlfMDCWrapper;

public class RemoteAuthenticationFilterTestBase {

  @Mock
  FilterChain filterChain;

  @Mock
  LoggerService loggerService;

  @Mock
  SlfMDCWrapper mdc;

  @Captor
  ArgumentCaptor<Authentication> newAuthentication;

  @InjectMocks
  RemoteAuthenticationFilter remoteAuthenticationFilter;

  @Mock
  ServletResponse servletResponse;

  @Mock
  UserEntityRepository userRepository;

  @BeforeEach
  public void setUp() {
    UserEntityRepositoryStubs.behaviour(userRepository);
  }

}
