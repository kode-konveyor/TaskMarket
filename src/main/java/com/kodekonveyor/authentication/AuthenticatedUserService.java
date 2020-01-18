package com.kodekonveyor.authentication;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.kodekonveyor.market.LogSeverityEnum;
import com.kodekonveyor.market.LoggerService;
import com.kodekonveyor.market.MarketConstants;

@Service
public class AuthenticatedUserService {

  @Autowired
  private LoggerService loggerService;

  @Autowired
  private UserEntityRepository userEntityRepository;

  public UserEntity call() {
    final String login = getNameForUser();
    loggerService.call(MarketConstants.LOGIN, LogSeverityEnum.INFO, login);
    checkCredential(login);
    final List<UserEntity> userList =
        userEntityRepository.findByLogin(login);
    if (userList.isEmpty())
      throw new NotLoggedInException(MarketConstants.THIS_SHOULD_NOT_HAPPEN);
    return userList.get(0);
  }

  private void checkAuthentication(final Authentication authentication) {
    if (null == authentication)
      throw new NotLoggedInException(MarketConstants.NO_AUTHENTICATION);
  }

  private void checkCredential(final String login) {
    if (null == login)
      throw new NotLoggedInException(MarketConstants.NO_CREDENTIAL);
  }

  private String getNameForUser() {
    final Authentication authentication =
        SecurityContextHolder.getContext().getAuthentication();
    checkAuthentication(authentication);
    return authentication.getName();
  }

}
