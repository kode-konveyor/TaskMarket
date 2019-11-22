package com.kodekonveyor.authentication;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.kodekonveyor.market.LogSeverityEnum;
import com.kodekonveyor.market.LoggerService;

@Service
public class AuthenticatedUserService {

  private static final String THIS_SHOULD_NOT_HAPPEN = "This should not happen";
  private static final String NO_AUTHENTICATION = "No Authentication";
  private static final String NO_CREDENTIAL = "No Credential";
  @Autowired
  private UserEntityRepository userEntityRepository;

  @Autowired
  private LoggerService loggerService;

  public UserEntity call() {
    final String login = getNameForUser();
    loggerService.call("login", LogSeverityEnum.INFO, login);
    checkCredential(login);
    final List<UserEntity> userList =
        userEntityRepository.findByLogin(login);
    if (userList.isEmpty())
      throw new NotLoggedInException(THIS_SHOULD_NOT_HAPPEN);
    return userList.get(0);
  }

  private String getNameForUser() {
    final Authentication authentication =
        SecurityContextHolder.getContext().getAuthentication();
    checkAuthentication(authentication);
    return authentication.getName();
  }

  private void checkCredential(final String login) {
    if (null == login)
      throw new NotLoggedInException(NO_CREDENTIAL);
  }

  private void checkAuthentication(final Authentication authentication) {
    if (null == authentication)
      throw new NotLoggedInException(NO_AUTHENTICATION);
  }

}
