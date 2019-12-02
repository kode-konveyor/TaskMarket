package com.kodekonveyor.authentication;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticationStubs {

  public static Authentication authentication;
  public static SecurityContext securityContext;

  public static void anonymous() {
    createSecurityContext();
    authentication = mock(Authentication.class);
    doReturn(false).when(authentication).isAuthenticated();
    doReturn(authentication).when(securityContext).getAuthentication();
    SecurityContextHolder.setContext(securityContext);
  }

  public static void authenticated() {
    createSecurityContext();
    authentication = mock(Authentication.class);
    doReturn(true).when(authentication).isAuthenticated();
    doReturn(authentication).when(securityContext).getAuthentication();
    doReturn(UserEntityTestData.LOGIN).when(authentication).getCredentials();
    doReturn(UserEntityTestData.LOGIN).when(authentication).getName();
    SecurityContextHolder.setContext(securityContext);
  }

  public static void badAuthenticated() {
    createSecurityContext();
    authentication = mock(Authentication.class);
    doReturn(true).when(authentication).isAuthenticated();
    doReturn(authentication).when(securityContext).getAuthentication();
    doReturn(UserEntityTestData.LOGIN_BAD).when(authentication)
        .getCredentials();
    doReturn(UserEntityTestData.LOGIN_BAD).when(authentication).getName();
    SecurityContextHolder.setContext(securityContext);
  }

  private static void createSecurityContext() {
    securityContext = mock(SecurityContext.class);
  }

  public static void nullAuthentication() {
    createSecurityContext();
    doReturn(null).when(securityContext).getAuthentication();
    SecurityContextHolder.setContext(securityContext);
  }

  public static void nullCredential() {
    createSecurityContext();
    authentication = mock(Authentication.class);
    doReturn(true).when(authentication).isAuthenticated();
    doReturn(authentication).when(securityContext).getAuthentication();
    doReturn(null).when(authentication).getCredentials();
    SecurityContextHolder.setContext(securityContext);
  }

}
