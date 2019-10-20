package com.kodekonveyor.market.servlets;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.kodekonveyor.market.LoggerService;
import com.kodekonveyor.market.NotLoggedInException;
import com.kodekonveyor.market.github.GithubGetService;
import com.kodekonveyor.market.login.User;
import com.kodekonveyor.market.login.UserDTO;
import com.kodekonveyor.market.login.UserMessages;
import com.kodekonveyor.market.login.UserRepository;

@RestController
public class RegistrationServlet implements UserMessages {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  GithubGetService githubGetService;

  @Autowired
  LoggerService loggerService;

  @Autowired
  ModelMapper modelMapper;

  @PostMapping("/member/user")
  @Transactional
  public UserDTO
      call(
          final HttpServletRequest request, @RequestBody final UserDTO userIn
      ) {
    loggerService.call("register:" + userIn.toString());
    final String remoteUser = request.getRemoteUser();
    if (null == remoteUser)
      throw new NotLoggedInException(PLEASE_LOG_IN, LOGIN_URL);
    loggerService.call("remoteUser:" + remoteUser);
    final String email = request.getHeader("OIDC_CLAIM_email");

    final User user =
        createUserFromGithub(remoteUser, userIn.getLogin(), email);
    updateUserFromDTO(userIn, user);
    return modelMapper.map(user, UserDTO.class);
  }

  private void updateUserFromDTO(final UserDTO userIn, final User user) {
    if (userIn.getAddress() != null)
      user.setAddress(userIn.getAddress());
    if (userIn.getEmail() != null)
      user.setEmail(userIn.getEmail());
    if (userIn.getName() != null)
      user.setName(userIn.getName());
    if (userIn.getCountry() != null)
      user.setCountry(userIn.getCountry());
    if (userIn.getCompany() != null)
      user.setCompany(userIn.getCompany());
    if (userIn.getRegistrationNumber() != null)
      user.setRegistrationNumber(userIn.getRegistrationNumber());
    if (userIn.getRepresentedBy() != null)
      user.setRepresentedBy(userIn.getRepresentedBy());
    if (userIn.getSkypeName() != null)
      user.setSkypeName(userIn.getSkypeName());
    if (userIn.getPaymentChannel() != null)
      user.setPaymentChannel(userIn.getPaymentChannel());
  }

  private User createUserFromGithub(
      final String auth0id, final String name, final String email
  ) {
    final Pattern pattern = Pattern.compile("github\\|(.*?)@.*");
    final Matcher matcher = pattern.matcher(auth0id);
    if (!matcher.find())
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, "No github id found"
      );
    final String userid = matcher.group(1);
    final List<User> users = userRepository.findByAuth0id(auth0id);
    if (users.size() > 0)
      throw new ResponseStatusException(
          HttpStatus.FORBIDDEN, "already registered:" + users.get(0).getLogin()
      );
    final UserDTO user = githubGetService.call("/users/" + name, UserDTO.class);
    loggerService.call("userid:" + userid);
    loggerService.call("user:" + user.toString());
    if (!user.getId().toString().equals(userid))
      throw new ResponseStatusException(
          HttpStatus.FORBIDDEN,
          "your provided username do not match the user you are logged in as"
      );
    final User userEntity = new User();
    userEntity.setAuth0id(auth0id);
    userEntity.setLogin(user.getLogin());
    userEntity.setEmail(email);
    userEntity.setName(user.getName());
    userEntity.setCountry(user.getCountry());
    loggerService.call("userEntity:" + userEntity.toString());
    userRepository.save(userEntity);
    return userEntity;
  }

}
