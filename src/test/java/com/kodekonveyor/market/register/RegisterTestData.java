package com.kodekonveyor.market.register;

import static org.mockito.Mockito.mock;

import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;

import com.kodekonveyor.authentication.UserDTO;
import com.kodekonveyor.authentication.UserTestData;

public class RegisterTestData {

  public final String NEXT_URL = "/foo.html";
  public final String GITHUB_EMAIL = "user@example.com";
  public final String GITHUB_USER = "userke";
  public final String GITHUB_SECRET = "s3cr3t";
  public StringWriter RESPONSE_WRITER;
  public UserDTO USER_DTO;
  public MarketUserDTO MARKET_USER;
  public HttpServletRequest AUTHENTICATED_REQUEST;
  public HttpServletRequest UNAUTHENTICATED_REQUEST;

  public final UserTestData userTestData;

  public RegisterTestData(final UserTestData userTestData) {
    this.userTestData = userTestData;
    USER_DTO = createUSER_DTO();
    MARKET_USER = createMARKET_USER();
    AUTHENTICATED_REQUEST =
        createAUTHENTICATED_REQUEST();
    UNAUTHENTICATED_REQUEST =
        create_UNAUTHENTICATED_REQUEST();
  }

  private HttpServletRequest
      createAUTHENTICATED_REQUEST() {
    return create_UNAUTHENTICATED_REQUEST();
  }

  private HttpServletRequest create_UNAUTHENTICATED_REQUEST() {
    return mock(HttpServletRequest.class);
  }

  private UserDTO createUSER_DTO() {
    final UserDTO userDTO = new UserDTO();
    userDTO.setLogin(GITHUB_USER);
    userDTO.setId(userTestData.USER_ID);
    return userDTO;
  }

  private MarketUserDTO createMARKET_USER() {
    final MarketUserDTO marketUserDTO = new MarketUserDTO();
    marketUserDTO.setLogin(userTestData.LOGIN);
    return marketUserDTO;
  }

}
