package com.kodekonveyor.authentication;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

class RemoteAuthenticationFilterTestData {

  public static final String AUTH_SESSION = "auth.session";
  public static final String AUTH_USER = "auth.user";
  public static final String AUTHENTICATING = "authenticating";
  public static final String EMPTY_MESSAGE = "";
  public static final String LOGIN = "login";
  public static final String LOGIN_URL = "/some/url";

  public static final String NAME_HEADER = "OIDC_CLAIM_nickname";
  public static final int EXPECTED_SET_AUTHENTICATION_CALLS = 2;

  public static final String SUCCESSFULLY_LOGGED_IN =
      "{} successfully logged in";

  public static final HttpServletRequest getRequest() {
    final HttpServletRequest request = mock(HttpServletRequest.class);
    final Enumeration<String> names =
        new Vector<>(List.of(NAME_HEADER)).elements();//NOPMD ArrayList cannot be converted to Enumeration
    doReturn(names).when(request).getHeaderNames();
    return request;
  }

  public static final HttpServletRequest getRequestUser() {
    final HttpServletRequest request = getRequest();
    doReturn(UserEntityTestData.LOGIN).when(request).getHeader(NAME_HEADER);
    return request;
  }

  public static final ServletRequest getRequestUserUnknown() {
    final HttpServletRequest request = getRequest();
    doReturn(UserEntityTestData.LOGIN_BAD).when(request).getHeader(NAME_HEADER);
    return request;
  }

}
