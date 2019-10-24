package com.kodekonveyor.market.authentication;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.kodekonveyor.market.login.User;
import com.kodekonveyor.market.login.UserRepository;

public class RemoteAuthenticationFilter implements Filter {

  @Autowired
  private UserRepository userRepository;

  @Override
  public void init(final FilterConfig fc) throws ServletException {

  }

  @Override
  public void doFilter(
      final ServletRequest req, final ServletResponse res, final FilterChain fc
  ) throws IOException, ServletException {
    final SecurityContext context = SecurityContextHolder.getContext();
    if (
      context.getAuthentication() != null &&
          context.getAuthentication().isAuthenticated()
    ) {
      // do nothing
    } else {
      final HttpServletRequest httpRequest = (HttpServletRequest) req;
      final String auth0id = httpRequest.getRemoteUser();
      final List<User> users =
          userRepository.findByAuth0id(auth0id);
      if (users.size() == 0)
        return;

      final Authentication auth = new RemoteAuthentication(users.get(0));
      SecurityContextHolder.getContext().setAuthentication(auth);
    }

    fc.doFilter(req, res);
  }

  @Override
  public void destroy() {

  }

}
