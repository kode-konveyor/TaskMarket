package com.kodekonveyor.authentication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.GenericFilterBean;

import com.kodekonveyor.annotations.InterfaceClass;
import com.kodekonveyor.market.LogSeverityEnum;
import com.kodekonveyor.market.LoggerService;
import com.kodekonveyor.market.SlfMDCWrapper;

@InterfaceClass
@Service
public class RemoteAuthenticationFilter extends GenericFilterBean
    implements Filter {

  private static final String NICKNAME_HEADER = "OIDC_CLAIM_nickname";

  @Autowired
  private LoggerService loggerService;

  @Autowired
  private SlfMDCWrapper mdc;

  @Autowired
  private UserEntityRepository userEntityRepository;

  private UserEntity createNewUserWithCredential(final String login) {
    final UserEntity newUser = new UserEntity();
    newUser.setLogin(login);
    userEntityRepository.save(newUser);
    return newUser;
  }

  @Override
  public void doFilter(
      final ServletRequest req, final ServletResponse res,
      final FilterChain filterChain
  ) throws IOException, ServletException {
    loggerService.call("authenticating", LogSeverityEnum.DEBUG, "");
    final HttpServletRequest httpRequest = (HttpServletRequest) req;

    logHeaders(httpRequest);

    final String login = httpRequest.getHeader(NICKNAME_HEADER);
    mdc.put("auth.user", login);
    mdc.put("auth.session", UUID.randomUUID().toString());

    loggerService.call("login", LogSeverityEnum.INFO, login);

    setAuthentication(new RemoteAuthentication(getOrCreateUser(login)));
    filterChain.doFilter(req, res);
    setAuthentication(null);
    mdc.clear();
  }

  private UserEntity getOrCreateUser(final String login) {
    final List<UserEntity> users =
        userEntityRepository.findByLogin(login);
    final UserEntity user =
        users.isEmpty() ? createNewUserWithCredential(login) : users.get(0);
    return user;
  }

  private void logHeaders(
      final HttpServletRequest httpRequest
  ) {
    final ArrayList<String> names =
        Collections.list(httpRequest.getHeaderNames());
    loggerService
        .call(
            "headers", LogSeverityEnum.DEBUG,
            ((Boolean) !names.isEmpty()).toString()
        );
    for (final String name : names)
      loggerService.call(
          "header", LogSeverityEnum.DEBUG,
          name + ":" + httpRequest.getHeader(name)
      );
  }

  private void setAuthentication(final Authentication auth) {
    SecurityContextHolder.getContext().setAuthentication(auth);
  }

}
