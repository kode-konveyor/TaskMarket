package com.kodekonveyor.authentication;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.GenericFilterBean;

import com.kodekonveyor.annotations.ExcludeFromCodeCoverage;
import com.kodekonveyor.annotations.InterfaceClass;
import com.kodekonveyor.market.LoggerService;

@InterfaceClass
@ExcludeFromCodeCoverage("interface to underlying framework")
@Service
public class RemoteAuthenticationFilter extends GenericFilterBean
    implements Filter {

  @Autowired
  private UserEntityRepository userEntityRepository;

  @Autowired
  private LoggerService loggerService;

  @Override
  public void doFilter(
      final ServletRequest req, final ServletResponse res,
      final FilterChain filterChain
  ) throws IOException, ServletException {
    if (loggerService == null) {
      final ServletContext servletContext = req.getServletContext();
      final WebApplicationContext webApplicationContext =
          WebApplicationContextUtils.getWebApplicationContext(servletContext);
      loggerService = webApplicationContext.getBean(LoggerService.class);
      userEntityRepository =
          webApplicationContext.getBean(UserEntityRepository.class);
    }
    loggerService.call("authenticating ");
    final SecurityContext context = SecurityContextHolder.getContext();
    if (
      context.getAuthentication() == null ||
          !context.getAuthentication().isAuthenticated()
    ) {
      final HttpServletRequest httpRequest = (HttpServletRequest) req;
      final String auth0id = httpRequest.getRemoteUser();
      loggerService.call("auth0id:" + auth0id);
      final List<UserEntity> users =
          userEntityRepository.findByAuth0id(auth0id);
      if (!users.isEmpty()) {
        final Authentication auth = new RemoteAuthenticationDTO(users.get(0));
        loggerService.call("authenticated " + auth.getPrincipal());
        SecurityContextHolder.getContext().setAuthentication(auth);
      }
    }

    filterChain.doFilter(req, res);
  }

}
