package com.kodekonveyor.market.register;

import com.kodekonveyor.authentication.AuthenticatedUserService;
import com.kodekonveyor.authentication.RoleEntity;
import com.kodekonveyor.authentication.RoleEntityRepository;
import com.kodekonveyor.authentication.UserEntity;
import com.kodekonveyor.authentication.UserEntityRepository;
import com.kodekonveyor.market.MarketConstants;
import com.kodekonveyor.market.UnauthorizedException;
import com.kodekonveyor.market.UrlMapConstants;
import com.kodekonveyor.market.ValidationException;
import com.kodekonveyor.market.lead.CheckRoleUtil;
import com.kodekonveyor.market.project.ProjectEntity;
import com.kodekonveyor.market.project.ProjectEntityRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.kodekonveyor.market.register.RegisterConstants.LOG_ADD_TO_ROLE_API_CALL;
import static com.kodekonveyor.market.register.RegisterConstants.LOG_ADD_TO_ROLE_API_FAILURE_CALL;
import static com.kodekonveyor.market.register.RegisterConstants.LOG_ADD_TO_ROLE_API_SUCCES_CALL;
import static com.kodekonveyor.market.register.RegisterConstants.NO_CAN_BE_PAID_ROLE;
import static com.kodekonveyor.market.register.RegisterConstants.NO_MANAGER_ROLE;
import static com.kodekonveyor.market.register.RegisterConstants.UNREGISTERED;

@RestController
public class AddToRoleController {

  @Autowired
  UserEntityRepository userEntityRepository;

  @Autowired
  RoleEntityRepository roleEntityRepository;

  @Autowired
  ProjectEntityRepository projectEntityRepository;

  @Autowired
  AuthenticatedUserService authenticatedUserService;

  @Autowired
  Logger logger;

  @PutMapping(UrlMapConstants.ADDPROJECTROLE_PATH)
  public void call(final String userName, final Long roleId) {
    logger.info(LOG_ADD_TO_ROLE_API_CALL, userName, roleId);

    final Optional<UserEntity> userToAddOptional =
        userEntityRepository.findByLogin(userName);
    if (userToAddOptional.isEmpty()) {
      logger.warn(LOG_ADD_TO_ROLE_API_FAILURE_CALL, userName, UNREGISTERED);
      throw new ValidationException(UNREGISTERED);
    }
    final RoleEntity role = roleEntityRepository.findById(roleId).get();

    final ProjectEntity project =
        projectEntityRepository.findByRole(role).get();
    final UserEntity user = authenticatedUserService.call();
    if (
      !CheckRoleUtil
          .hasRole(user, project, MarketConstants.MANAGER)
    ) {
      logger.warn(LOG_ADD_TO_ROLE_API_FAILURE_CALL, userName, NO_MANAGER_ROLE);
      throw new UnauthorizedException(NO_MANAGER_ROLE);
    }
    final UserEntity userToAdd =
        userToAddOptional.get();
    final ProjectEntity kodeKonveyorProject = projectEntityRepository
        .findByName(MarketConstants.KODE_KONVEYOR_PROJECT_NAME).get();
    if (
      !CheckRoleUtil
          .hasRole(
              userToAdd, kodeKonveyorProject, MarketConstants.CAN_BE_PAID_ROLE
          )
    ) {
      logger.warn(LOG_ADD_TO_ROLE_API_FAILURE_CALL, userName, NO_CAN_BE_PAID_ROLE + userToAdd);
      throw new UnauthorizedException(
              NO_CAN_BE_PAID_ROLE + userToAdd
      );
    }
    final Set<RoleEntity> roles = new HashSet<>();
    roles.addAll(userToAdd.getRole());
    roles.add(role);
    userToAdd.setRole(roles);
    userEntityRepository.save(userToAdd);
    logger.debug(LOG_ADD_TO_ROLE_API_SUCCES_CALL);
  }

}
