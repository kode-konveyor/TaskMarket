package com.kodekonveyor.authentication;

import javax.servlet.FilterChain;
import javax.servlet.ServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.Authentication;

import com.kodekonveyor.market.LogTestData;
import com.kodekonveyor.market.LoggerService;
import com.kodekonveyor.market.SlfMDCWrapper;

public class RemoteAuthenticationFilterTestBase {

  @InjectMocks
  RemoteAuthenticationFilter remoteAuthenticationFilter;

  @Mock
  UserEntityRepository userRepository;

  @Mock
  LoggerService loggerService;

  @Mock
  ServletResponse servletResponse;

  @Mock
  FilterChain filterChain;

  @Mock
  SlfMDCWrapper mdc;

  WebAppTestData testData;

  @Captor
  ArgumentCaptor<Authentication> newAuthentication;

  UserTestData userTestData;

  LogTestData logTestData;

  @BeforeEach
  public void setUp() {
    userTestData = new UserTestData();
    testData = new WebAppTestData(userTestData);
    logTestData = new LogTestData();
    UserEntityRepositoryStubs.behaviour(userRepository, userTestData);
  }

}
