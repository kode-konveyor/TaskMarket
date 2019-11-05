package com.kodekonveyor.authentication;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

public class WebappTestData {

  public final HttpServletRequest REQUEST;
  public final ServletRequest REQUEST_WITH_UNKNOWN_USER;
  public final String NOT_LOGGED_IN = "not logged in";
  public final String LOGIN_URL = "/some/url";
  private final UserTestData userTestData; // NOPMD

  public WebappTestData(final UserTestData userTestData) {
    this.userTestData = userTestData;
    REQUEST = createREQUEST();
    REQUEST_WITH_UNKNOWN_USER = createREQUEST_WITH_UNKNOWN_USER();
  }

  private HttpServletRequest createREQUEST() {
    final HttpServletRequest response = mock(HttpServletRequest.class);
    doReturn(userTestData.AUTH0ID).when(response).getRemoteUser();
    return response;
  }

  private ServletRequest createREQUEST_WITH_UNKNOWN_USER() {
    final HttpServletRequest response = mock(HttpServletRequest.class);
    doReturn(userTestData.BAD_AUTH0ID).when(response).getRemoteUser();
    return response;
  }

}
