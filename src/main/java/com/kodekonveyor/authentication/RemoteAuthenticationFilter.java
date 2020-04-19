package com.kodekonveyor.authentication;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.GenericFilterBean;

import com.kodekonveyor.annotations.ExcludeFromCodeCoverage;
import com.kodekonveyor.annotations.InterfaceClass;

@InterfaceClass
@ExcludeFromCodeCoverage("interface to underlying framework")
public class RemoteAuthenticationFilter extends GenericFilterBean
    implements Filter {

  @Override
  public void doFilter(
      final ServletRequest request, final ServletResponse response,
      final FilterChain filterChain
  ) throws IOException, ServletException {

    final Logger loggerService =
        LoggerFactory.getLogger(RemoteAuthenticationService.class);
    final UserEntityRepository userEntityRepository =
        getWebApplicationContext(request).getBean(UserEntityRepository.class);

    new RemoteAuthenticationService(
        userEntityRepository, loggerService
    ).call(request, response, filterChain);
  }

  private WebApplicationContext
      getWebApplicationContext(final ServletRequest request) {
    return WebApplicationContextUtils
        .getWebApplicationContext(request.getServletContext());
  }

}
