package com.kodekonveyor.market.login;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodekonveyor.market.LoggerService;
import com.kodekonveyor.market.NotLoggedInException;

@RestController
public class UserController implements UserMessages {

  @Autowired
  LoggerService loggerService;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  ModelMapper modelMapper;

  @GetMapping("/member/user")
  @Transactional
  public UserDTO call(final HttpServletRequest request) {
    final String remoteUser = request.getRemoteUser();
    if (null == remoteUser)
      throw new NotLoggedInException(PLEASE_LOG_IN, LOGIN_URL);
    loggerService.call("remoteUser:" + remoteUser);
    final List<User> users =
        userRepository.findByAuth0id(remoteUser);
    if (users.size() == 0) {
      final UserDTO user = new UserDTO();
      user.setAuth0id(remoteUser);
      return user;
    }
    final User user = users.get(0);
    return modelMapper.map(user, UserDTO.class);

  }
}
