package com.kodekonveyor.authentication;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;//NOPMD

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.core.Authentication;

import com.kodekonveyor.annotations.TestedBehaviour;
import com.kodekonveyor.annotations.TestedService;
import com.kodekonveyor.market.GeneralTestHelper;
import com.kodekonveyor.market.LoggerService;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("Puts the remote user into the Authentication object")
@TestedService("RemoteAuthenticationFilter")
public class RemoteAuthenticationFilterTest {

  @InjectMocks
  private RemoteAuthenticationFilter remoteAuthenticationFilter;

  @Mock
  private UserEntityRepository userRepository;

  @Mock
  private LoggerService loggerService;

  @Mock
  private ServletResponse servletResponse;

  @Mock
  private FilterChain filterChain;

  private WebappTestData testData;

  @Captor
  private ArgumentCaptor<Authentication> newAuthentication;

  @Captor
  private ArgumentCaptor<String> stringCaptor;

  private UserTestData userTestData;

  @BeforeEach
  public void setUp() {
    userTestData = new UserTestData();
    testData = new WebappTestData(userTestData);
    UserEntityRepositoryStubs.behaviour(userRepository, userTestData);
  }

  @DisplayName("if authenticated, does not set authenticaed user")
  @Test
  public void test() throws IOException, ServletException {
    AuthenticationStubs.authenticated(userTestData);
    remoteAuthenticationFilter
        .doFilter(testData.REQUEST, servletResponse, filterChain);
    verify(AuthenticationStubs.securityContext, never())
        .setAuthentication(newAuthentication.capture());
  }

  @DisplayName("if authenticated, calls the filter chain")
  @Test
  public void test01() throws IOException, ServletException {
    AuthenticationStubs.authenticated(userTestData);
    remoteAuthenticationFilter
        .doFilter(testData.REQUEST, servletResponse, filterChain);
    verify(filterChain).doFilter(testData.REQUEST, servletResponse);
  }

  @DisplayName("if the filter authenticates someone, logs the authentication")
  @Test
  public void test02() throws IOException, ServletException {
    AuthenticationStubs.nullAuthentication();
    remoteAuthenticationFilter
        .doFilter(testData.REQUEST, servletResponse, filterChain);
    verify(loggerService, times(3)).call(stringCaptor.capture());
    GeneralTestHelper.assertContains("authenticated", stringCaptor.getValue());
  }

  @DisplayName(
    "if Authentication is null, sets the remote user as authenticated"
  )
  @Test
  public void test1() throws IOException, ServletException {
    AuthenticationStubs.nullAuthentication();
    remoteAuthenticationFilter
        .doFilter(testData.REQUEST, servletResponse, filterChain);
    assertRemoteUserIsCorrectlySet(userTestData.LOGIN);
  }

  @DisplayName(
    "if Authentication is anonymous, sets the remote user as authenticated"
  )
  @Test
  public void test2() throws IOException, ServletException {
    AuthenticationStubs.anonymous();
    remoteAuthenticationFilter
        .doFilter(testData.REQUEST, servletResponse, filterChain);
    assertRemoteUserIsCorrectlySet(userTestData.LOGIN);
  }

  @DisplayName(
    "if Authentication is null and the remote user exists, sets the remote user as authenticated"
  )
  @Test
  public void test3() throws IOException, ServletException {
    AuthenticationStubs.nullAuthentication();
    remoteAuthenticationFilter
        .doFilter(testData.REQUEST, servletResponse, filterChain);
    assertRemoteUserIsCorrectlySet(userTestData.LOGIN);
  }

  private void assertRemoteUserIsCorrectlySet(final String login) {
    verify(AuthenticationStubs.securityContext)
        .setAuthentication(newAuthentication.capture());
    assertEquals(
        login, newAuthentication.getValue().getCredentials()
    );
  }

  @DisplayName(
    "if Authentication is null and the remote user does not exists, creates an authenticated user"
  )
  @Test
  public void test4() throws IOException, ServletException {
    AuthenticationStubs.nullAuthentication();
    remoteAuthenticationFilter.doFilter(
        testData.REQUEST_WITH_UNKNOWN_USER, servletResponse, filterChain
    );
    assertRemoteUserIsCorrectlySet(userTestData.BAD_LOGIN);
  }

}
