package com.kodekonveyor.market.register;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodekonveyor.authentication.RoleUtil;

@Service
public class MarketUserCompilerService {

  @Autowired
  MarketUserEntityRepository marketUserEntityRepository; //NOPMD

  public Object call(final Long userId) {

    final Optional<MarketUserEntity> entities =
        marketUserEntityRepository.findById(userId);
    roles(entities);

    return null;

  }

  private void roles(final Optional<MarketUserEntity> entities) {
    final MarketUserEntity entity = entities.get();

    if (
      entity.getLegal() != null
    ) {
      entity.getLogin().setRoles(Set.of(RoleUtil.getNameCanBePayed()));
      marketUserEntityRepository.save(entity);

      //      if (
      //        CheckRoleUtil
      //            .hasRole(entity.getLogin(), MarketConstants.CAN_BE_PAID_ROLE)
      //      )
      //        return;
    }

    if (
      entity.getLegal() == null
    ) {
      entity.getLogin().setRoles(Set.of());
      marketUserEntityRepository.save(entity);

      //      if (
      //        !CheckRoleUtil
      //            .hasRole(entity.getLogin(), MarketConstants.CAN_BE_PAID_ROLE)
      //      )
      //
      //        return;
    }

  }

}
