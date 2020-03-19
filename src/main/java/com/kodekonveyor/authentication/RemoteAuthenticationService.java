package com.kodekonveyor.authentication;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.kodekonveyor.annotations.InterfaceClass;
import com.kodekonveyor.logging.LoggingMarkerConstants;

@InterfaceClass
public class RemoteAuthenticationService { //NOPMD This class should not be managed by Spring

  private final UserEntityRepository userEntityRepository;

  private final Logger loggerService;

  public RemoteAuthenticationService(
      final UserEntityRepository userEntityRepository,
      final Logger loggerService
  ) {
    this.userEntityRepository = userEntityRepository;
    this.loggerService = loggerService;
  }

  public void
      call(
          final ServletRequest req, final ServletResponse res,
          final FilterChain filterChain
      ) throws IOException, ServletException {
    final HttpServletRequest httpRequest = (HttpServletRequest) req;
    final SecurityContext context = SecurityContextHolder.getContext();

    final String login =
        httpRequest.getHeader(AuthenticationConstants.NICKNAME_HEADER);

    loggerService.info(
        LoggingMarkerConstants.AUTHENTICATION,
        AuthenticationConstants.SUCCESSFULLY_LOGGED_IN, login
    );

    final Optional<UserEntity> maybeUser =
        userEntityRepository.findByLogin(login).stream().findFirst();
    final UserEntity user =
        maybeUser.orElseGet(() -> createNewUserWithCredential(login));

    final Authentication auth = new RemoteAuthentication(user);
    SecurityContextHolder.getContext().setAuthentication(auth);
    filterChain.doFilter(req, res);
    context.setAuthentication(null);
  }

  private UserEntity createNewUserWithCredential(final String login) {
    final UserEntity newUser = new UserEntity();
    newUser.setLogin(login);
    userEntityRepository.save(newUser);
    return newUser;
  }

}
