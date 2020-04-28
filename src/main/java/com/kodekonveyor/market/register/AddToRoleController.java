package com.kodekonveyor.market.register;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

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

  @PutMapping(UrlMapConstants.ADDPROJECTROLE_PATH)
  public void call(final String userName, final Long roleId) {
    final Optional<UserEntity> userToAddOptional =
        userEntityRepository.findByLogin(userName);
    if (userToAddOptional.isEmpty())
      throw new ValidationException(RegisterConstants.UNREGISTERED);

    final RoleEntity role = roleEntityRepository.findById(roleId).get();

    final ProjectEntity project =
        projectEntityRepository.findByRole(role).get();
    final UserEntity user = authenticatedUserService.call();
    if (
      !CheckRoleUtil
          .hasRole(user, project, MarketConstants.MANAGER)
    )
      throw new UnauthorizedException(RegisterConstants.NO_MANAGER_ROLE);

    final UserEntity userToAdd =
        userToAddOptional.get();
    final ProjectEntity kodeKonveyorProject = projectEntityRepository
        .findByName(MarketConstants.KODE_KONVEYOR_PROJECT_NAME).get();
    if (
      !CheckRoleUtil
          .hasRole(
              userToAdd, kodeKonveyorProject, MarketConstants.CAN_BE_PAID_ROLE
          )
    )
      throw new UnauthorizedException(
          RegisterConstants.NO_CAN_BE_PAID_ROLE + userToAdd
      );

    final Set<RoleEntity> roles = new HashSet<>();
    roles.addAll(userToAdd.getRole());
    roles.add(role);
    userToAdd.setRole(roles);
    userEntityRepository.save(userToAdd);

  }

}
