package com.kodekonveyor.authentication;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

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
import com.kodekonveyor.market.LogSeverityEnum;
import com.kodekonveyor.market.LoggerService;
import com.kodekonveyor.market.SlfMDCWrapper;

@InterfaceClass
@ExcludeFromCodeCoverage("interface to underlying framework")
@Service
public class RemoteAuthenticationFilter extends GenericFilterBean
    implements Filter {

  private static final String NICKNAME_HEADER = "OIDC_CLAIM_nickname";

  @Autowired
  private UserEntityRepository userEntityRepository;

  @Autowired
  private LoggerService loggerService;

  @Autowired
  private SlfMDCWrapper mdc;

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
      mdc =
          webApplicationContext.getBean(SlfMDCWrapper.class);
    }
    loggerService.call("authenticating", LogSeverityEnum.DEBUG, "");
    final HttpServletRequest httpRequest = (HttpServletRequest) req;
    final SecurityContext context = SecurityContextHolder.getContext();
    final Enumeration<String> names = httpRequest.getHeaderNames();
    loggerService
        .call(
            "headers", LogSeverityEnum.DEBUG,
            ((Boolean) names.hasMoreElements()).toString()
        );
    while (names.hasMoreElements()) {
      final String name = names.nextElement();
      loggerService.call(
          "header", LogSeverityEnum.DEBUG,
          name + ":" + httpRequest.getHeader(name)
      );
    }
    final String login = httpRequest.getHeader(NICKNAME_HEADER);
    mdc.put("auth.user", login);
    mdc.put("auth.session", UUID.randomUUID().toString());
    loggerService.call("login", LogSeverityEnum.INFO, login);
    final List<UserEntity> users =
        userEntityRepository.findByLogin(login);
    UserEntity user;
    if (users.isEmpty()) {
      user = createNewUserWithCredential(login);
      userEntityRepository.save(user);
    } else
      user = users.get(0);
    final Authentication auth = new RemoteAuthentication(user);
    SecurityContextHolder.getContext().setAuthentication(auth);
    filterChain.doFilter(req, res);
    context.setAuthentication(null);
    mdc.clear();

  }

  private UserEntity createNewUserWithCredential(final String login) {
    final UserEntity newUser = new UserEntity();
    newUser.setLogin(login);
    userEntityRepository.save(newUser);
    return newUser;
  }

}
