package com.kodekonveyor.authentication;

import static org.junit.Assert.assertEquals;
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
import com.kodekonveyor.logging.LoggingMarkerConstants;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("Puts the remote user into the Authentication object")
@TestedService("RemoteAuthenticationFilter")
public class RemoteAuthenticationServiceTest
    extends RemoteAuthenticationServiceTestBase {

  private void assertRemoteUserIsCorrectlySetAndCleared(final String login) {
    verify(
        AuthenticationStubs.securityContext,
        times(
            RemoteAuthenticationFilterTestData.EXPECTED_SET_AUTHENTICATION_CALLS
        )
    )
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
    final HttpServletRequest request =
        RemoteAuthenticationFilterTestData.getRequestUser();
    remoteAuthenticationService
        .call(
            request, res, filterChain
        );
    verify(filterChain).doFilter(
        request, res
    );
  }

  @DisplayName("logs the authenticated user")
  @Test
  public void test03() throws IOException, ServletException {
    AuthenticationStubs.nullAuthentication();
    remoteAuthenticationService
        .call(
            RemoteAuthenticationFilterTestData.getRequestUser(), res,
            filterChain
        );
    verify(loggerService)
        .info(
            LoggingMarkerConstants.AUTHENTICATION,
            RemoteAuthenticationFilterTestData.SUCCESSFULLY_LOGGED_IN,
            UserEntityTestData.LOGIN
        );
  }

  @DisplayName(
    "if Authentication is null, sets the remote user as authenticated, and clears it after the request is processed"
  )
  @Test
  public void test1() throws IOException, ServletException {
    AuthenticationStubs.nullAuthentication();
    remoteAuthenticationService
        .call(
            RemoteAuthenticationFilterTestData.getRequestUser(), res,
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
    remoteAuthenticationService
        .call(
            RemoteAuthenticationFilterTestData.getRequestUser(), res,
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
    remoteAuthenticationService
        .call(
            RemoteAuthenticationFilterTestData.getRequestUser(), res,
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
    remoteAuthenticationService.call(
        RemoteAuthenticationFilterTestData.getRequestUserUnknown(), res,
        filterChain
    );
    assertRemoteUserIsCorrectlySetAndCleared(UserEntityTestData.LOGIN_BAD);
  }

}
