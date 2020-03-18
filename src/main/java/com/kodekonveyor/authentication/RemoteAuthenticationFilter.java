package com.kodekonveyor.authentication;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
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

  private UserEntityRepository userEntityRepository;

  @Override
  public void doFilter(
      final ServletRequest req, final ServletResponse res,
      final FilterChain filterChain
  ) throws IOException, ServletException {

    final WebApplicationContext webApplicationContext =
        getWebApplicationContext(req);
    final Logger loggerService =
        LoggerFactory.getLogger(RemoteAuthenticationService.class);
    userEntityRepository =
        webApplicationContext.getBean(UserEntityRepository.class);

    new RemoteAuthenticationService(
        userEntityRepository, loggerService
    ).call(req, res, filterChain);
  }

  private WebApplicationContext
      getWebApplicationContext(final ServletRequest req) {
    final ServletContext servletContext = req.getServletContext();
    final WebApplicationContext webApplicationContext =
        WebApplicationContextUtils.getWebApplicationContext(servletContext);
    return webApplicationContext;
  }

}
