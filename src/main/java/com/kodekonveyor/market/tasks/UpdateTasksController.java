package com.kodekonveyor.market.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodekonveyor.authentication.AuthenticatedUserService;
import com.kodekonveyor.authentication.UserEntity;
import com.kodekonveyor.market.MarketConstants;
import com.kodekonveyor.market.UnauthorizedException;
import com.kodekonveyor.market.UrlMapConstants;
import com.kodekonveyor.market.lead.CheckRoleUtil;
import com.kodekonveyor.market.project.ProjectEntity;
import com.kodekonveyor.market.project.ProjectEntityRepository;
import com.kodekonveyor.market.register.RegisterConstants;

@RestController
public class UpdateTasksController {

  @Autowired
  AuthenticatedUserService authenticatedUserService;
  @Autowired
  ProjectEntityRepository projectEntityRepository;

  @PutMapping(UrlMapConstants.TASK_UPDATE_PATH)
  public Object call() {
    final UserEntity user = authenticatedUserService.call();
    checkRole(user);
    return null;
  }

  private void checkRole(final UserEntity user) {
    final ProjectEntity project =
        projectEntityRepository
            .findByName(MarketConstants.KODE_KONVEYOR_PROJECT_NAME).get();

    if (
      !CheckRoleUtil
          .hasRole(user, project, MarketConstants.KODEKONVEYOR_CONTRACT)
    )
      throw new UnauthorizedException(
          RegisterConstants.NO_CAN_BE_PAID_ROLE
      );
  }
}
