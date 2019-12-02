package com.kodekonveyor.authentication;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.core.Authentication;

import com.kodekonveyor.annotations.TestedBehaviour;
import com.kodekonveyor.annotations.TestedService;
import com.kodekonveyor.market.LogSeverityEnum;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("Puts the remote user into the Authentication object")
@TestedService("RemoteAuthenticationFilter")
public class RemoteAuthenticationFilterTest
    extends RemoteAuthenticationFilterTestBase {

  private void assertRemoteUserIsCorrectlySetAndCleared(final String login) {
    verify(AuthenticationStubs.securityContext, times(2))
        .setAuthentication(newAuthentication.capture());
    final List<Authentication> capturedValues =
        newAuthentication.getAllValues();
    assertEquals(
        login, capturedValues.get(0).getCredentials()
    );
    assertEquals(null, capturedValues.get(1));
  }

  @DisplayName("if authenticated, calls the filter chain")
  @Test
  public void test01() throws IOException, ServletException {
    AuthenticationStubs.authenticated();
    HttpServletRequest request = RemoteAuthenticationFilterTestData.getRequestUser();
    remoteAuthenticationFilter
        .doFilter(
            request,
            servletResponse,
            filterChain
        );
    verify(filterChain).doFilter(
        request, servletResponse
    );
  }

  @DisplayName(
    "logs the authentication attempt"
  )
  @Test
  public void test02() throws IOException, ServletException {
    AuthenticationStubs.nullAuthentication();
    remoteAuthenticationFilter
        .doFilter(
            RemoteAuthenticationFilterTestData.getRequestUser(),
            servletResponse,
            filterChain
        );
    verify(loggerService)
        .call(
            RemoteAuthenticationFilterTestData.AUTHENTICATING,
            LogSeverityEnum.DEBUG,
            RemoteAuthenticationFilterTestData.EMPTY_MESSAGE
        );
  }

  @DisplayName("logs the authenticated user")
  @Test
  public void test03() throws IOException, ServletException {
    AuthenticationStubs.nullAuthentication();
    remoteAuthenticationFilter
        .doFilter(
            RemoteAuthenticationFilterTestData.getRequestUser(),
            servletResponse,
            filterChain
        );
    verify(loggerService)
        .call(
            RemoteAuthenticationFilterTestData.LOGIN, LogSeverityEnum.INFO,
            UserEntityTestData.LOGIN
        );
  }

  @DisplayName(
    "if Authentication is null, sets the remote user as authenticated, and clears it after the request is processed"
  )
  @Test
  public void test1() throws IOException, ServletException {
    AuthenticationStubs.nullAuthentication();
    remoteAuthenticationFilter
        .doFilter(
            RemoteAuthenticationFilterTestData.getRequestUser(),
            servletResponse,
            filterChain
        );
    assertRemoteUserIsCorrectlySetAndCleared(UserEntityTestData.LOGIN);
  }

  @DisplayName(
    "if Authentication is anonymous, sets the remote user as authenticated, and clears it after the request is processed"
  )
  @Test
  public void test2() throws IOException, ServletException {
    AuthenticationStubs.anonymous();
    remoteAuthenticationFilter
        .doFilter(
            RemoteAuthenticationFilterTestData.getRequestUser(),
            servletResponse,
            filterChain
        );
    assertRemoteUserIsCorrectlySetAndCleared(UserEntityTestData.LOGIN);
  }

  @DisplayName(
    "if Authentication is null and the remote user exists, sets the remote user as authenticated, and clears it after the request is processed"
  )
  @Test
  public void test3() throws IOException, ServletException {
    AuthenticationStubs.nullAuthentication();
    remoteAuthenticationFilter
        .doFilter(
            RemoteAuthenticationFilterTestData.getRequestUser(),
            servletResponse,
            filterChain
        );
    assertRemoteUserIsCorrectlySetAndCleared(UserEntityTestData.LOGIN);
  }

  @DisplayName(
    "if Authentication is null and the remote user does not exists, creates an authenticated user, and clears authentication after the request is processed"
  )
  @Test
  public void test4() throws IOException, ServletException {
    AuthenticationStubs.nullAuthentication();
    remoteAuthenticationFilter.doFilter(
        RemoteAuthenticationFilterTestData.getRequestUserUnknown(),
        servletResponse, filterChain
    );
    assertRemoteUserIsCorrectlySetAndCleared(UserEntityTestData.LOGIN_BAD);
  }

  @DisplayName("puts the username to Mapped Diagnostic Context for log")
  @Test
  public void testMdc1() throws IOException, ServletException {
    AuthenticationStubs.authenticated();
    remoteAuthenticationFilter
        .doFilter(
            RemoteAuthenticationFilterTestData.getRequestUser(),
            servletResponse,
            filterChain
        );
    verify(mdc).put(
        RemoteAuthenticationFilterTestData.AUTH_USER, UserEntityTestData.LOGIN
    );
  }

  @DisplayName("puts the session id to Mapped Diagnostic Context for log")
  @Test
  public void testMdc2() throws IOException, ServletException {
    AuthenticationStubs.authenticated();
    remoteAuthenticationFilter
        .doFilter(
            RemoteAuthenticationFilterTestData.getRequestUser(),
            servletResponse,
            filterChain
        );
    verify(mdc)
        .put(eq(RemoteAuthenticationFilterTestData.AUTH_SESSION), anyString());
  }

}
