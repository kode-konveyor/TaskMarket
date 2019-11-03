package com.kodekonveyor.market.login;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.modelmapper.ModelMapper;

import com.kodekonveyor.authentication.UserEntity;

import lombok.Getter;

@Getter
public class RegisterTestData {

  public final UserEntity USER_ENTITY = getUSER_ENTITY();
  public final List<UserEntity> USER_LIST = List.of(USER_ENTITY);
  public final String REGISTER_LOG =
      "register:UserDTO(login=userke, id=111, email=user@example.com, auth0id=null";
  public final String GITHUB_USERID = "111";
  public final String GITHUB_EMAIL = "user@example.com";
  public final String GITHUB_USER = "userke";
  public final String AUTH0_USER =
      "github|" + GITHUB_USERID + "@eu.example.com";
  public final String GITHUB_SECRET = "s3cr3t";
  public StringWriter RESPONSE_WRITER;
  public UserDTO USER_DTO = getUSER_DTO();
  public HttpServletRequest AUTHENTICATED_REQUEST = getAUTHENTICATED_REQUEST();
  public HttpServletRequest UNAUTHENTICATED_REQUEST =
      get_UNAUTHENTICATED_REQUEST();

  public HttpServletRequest
      getAUTHENTICATED_REQUEST() {
    final HttpServletRequest request = get_UNAUTHENTICATED_REQUEST();
    doReturn(AUTH0_USER).when(request).getRemoteUser();
    return request;
  }

  public UserEntity getUSER_ENTITY() {
    final ModelMapper mapper = new ModelMapper();
    return mapper.map(getUSER_DTO(), UserEntity.class);

  }

  public HttpServletRequest get_UNAUTHENTICATED_REQUEST() {
    return mock(HttpServletRequest.class);
  }

  public HttpServletResponse getRESPONSE() throws IOException {
    final HttpServletResponse resp = mock(HttpServletResponse.class);
    RESPONSE_WRITER = getRESPONSE_WRITER();
    final PrintWriter writer = new PrintWriter(RESPONSE_WRITER);
    doReturn(writer).when(resp).getWriter();
    return resp;
  }

  public UserDTO getUSER_DTO() {
    final UserDTO userDTO = new UserDTO();
    userDTO.setLogin(GITHUB_USER);
    userDTO.setEmail(GITHUB_EMAIL);
    userDTO.setId(Long.parseLong(GITHUB_USERID));
    return userDTO;
  }

  public StringWriter getRESPONSE_WRITER() {
    return new StringWriter();
  }

  public HttpServletRequest getREQUEST_AUTHENTICATED() {
    final HttpServletRequest req = mock(HttpServletRequest.class);
    final ServletContext context = mock(ServletContext.class);
    doReturn(context).when(req).getServletContext();

    doReturn(AUTH0_USER).when(req).getRemoteUser();
    return req;
  }

}
