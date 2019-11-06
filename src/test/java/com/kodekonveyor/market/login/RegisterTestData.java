package com.kodekonveyor.market.login;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;

import com.kodekonveyor.authentication.UserTestData;
import com.kodekonveyor.market.register.MarketUserDTO;

public class RegisterTestData {

  public final String REGISTER_LOG =
      "register:UserDTO(login=userke, id=111, email=user@example.com, auth0id=null";
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
    final HttpServletRequest request = create_UNAUTHENTICATED_REQUEST();
    doReturn(userTestData.AUTH0ID).when(request).getRemoteUser();
    return request;
  }

  private HttpServletRequest create_UNAUTHENTICATED_REQUEST() {
    return mock(HttpServletRequest.class);
  }

  private UserDTO createUSER_DTO() {
    final UserDTO userDTO = new UserDTO();
    userDTO.setLogin(GITHUB_USER);
    userDTO.setEmail(GITHUB_EMAIL);
    userDTO.setId(Long.parseLong(userTestData.LOGIN));
    return userDTO;
  }

  private MarketUserDTO createMARKET_USER() {
    final MarketUserDTO marketUserDTO = new MarketUserDTO();
    marketUserDTO.setAuth0id(userTestData.AUTH0ID);
    marketUserDTO.setLogin(userTestData.LOGIN);
    return marketUserDTO;
  }

}
