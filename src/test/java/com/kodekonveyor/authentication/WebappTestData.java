package com.kodekonveyor.authentication;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

public class WebappTestData {

  private final String NAME_HEADER = "OIDC_CLAIM_nickname";
  public final HttpServletRequest REQUEST;
  public final ServletRequest REQUEST_WITH_UNKNOWN_USER;
  public final String NOT_LOGGED_IN = "not logged in";
  public final String LOGIN_URL = "/some/url";
  private final UserTestData userTestData; // NOPMD
  public String UNAUTHORIZED = "unauthorized";

  public WebappTestData(final UserTestData userTestData) {
    this.userTestData = userTestData;
    REQUEST = createREQUEST();
    REQUEST_WITH_UNKNOWN_USER = createREQUEST_WITH_UNKNOWN_USER();
  }

  private HttpServletRequest createREQUEST() {
    final HttpServletRequest request = mock(HttpServletRequest.class);
    doReturn(userTestData.LOGIN).when(request).getHeader(NAME_HEADER);
    createHEADER_NAMES(request);
    return request;
  }

  private void createHEADER_NAMES(final HttpServletRequest request) {
    final Enumeration<String> names =
        new Vector<>(List.of(NAME_HEADER)).elements();//NOPMD ArrayList cannot be converted to Enumeration
    doReturn(names).when(request).getHeaderNames();
  }

  private ServletRequest createREQUEST_WITH_UNKNOWN_USER() {
    final HttpServletRequest request = mock(HttpServletRequest.class);
    createHEADER_NAMES(request);
    doReturn(userTestData.BAD_LOGIN).when(request).getHeader(NAME_HEADER);
    return request;
  }

}
