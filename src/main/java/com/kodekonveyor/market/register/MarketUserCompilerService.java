package com.kodekonveyor.market.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodekonveyor.authentication.AuthenticatedUserService;
import com.kodekonveyor.authentication.UserEntity;
import com.kodekonveyor.market.MarketConstants;
import com.kodekonveyor.market.UnauthorizedException;
import com.kodekonveyor.market.lead.CheckRoleUtil;
import com.kodekonveyor.market.project.ProjectEntity;
import com.kodekonveyor.market.project.ProjectEntityRepository;

@Service
public class MarketUserCompilerService {

  @Autowired
  private AuthenticatedUserService authenticatedUserService;

  @Autowired
  private ProjectEntityRepository projectEntityRepository;

  @Autowired
  private MarketUserEntityRepository marketUserEntityRepository;

  public Object call(final Long userId) {
    final UserEntity user = authenticatedUserService.call();
    final MarketUserEntity returnedUser =
        marketUserEntityRepository.findById(userId).get();
    final ProjectEntity project =
        projectEntityRepository
            .findByName(MarketConstants.KODE_KONVEYOR_PROJECT_NAME).get();
    if (returnedUser.getUser().getId() == user.getId()) {
      if (
        !CheckRoleUtil.hasRole(user, project, MarketConstants.CAN_BE_PAID_ROLE)
      )
        throw new UnauthorizedException(
            RegisterConstants.NO_CAN_BE_PAID_ROLE
        );
    } else if (
      !CheckRoleUtil
          .hasRole(user, project, MarketConstants.KODEKONVEYOR_CONTRACT)
    )
      throw new UnauthorizedException(
          RegisterConstants.NO_CONTRACT_ROLE
      );

    return null;
  }

}
