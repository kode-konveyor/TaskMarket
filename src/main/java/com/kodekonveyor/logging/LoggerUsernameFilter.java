package com.kodekonveyor.logging;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.MDC;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class LoggerUsernameFilter implements Filter {

  private static final String USER_NAME = "userName";

  @Override
  public void init(final FilterConfig filterConfig) throws ServletException {
  }

  @Override
  public void doFilter(
      final ServletRequest req, final ServletResponse resp,
      final FilterChain chain
  )
      throws IOException, ServletException {
    final Authentication authentication =
        SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null)
      MDC.put(USER_NAME, authentication.getName());
    try {
      chain.doFilter(req, resp);
    } finally {
      if (authentication != null)
        MDC.remove(USER_NAME);
    }
  }

  @Override
  public void destroy() {
  }

}
