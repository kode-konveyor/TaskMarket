package com.kodekonveyor.market.authentication;

import static org.mockito.Mockito.doReturn;

import java.util.Optional;

import com.kodekonveyor.authentication.RoleEntityRepository;
import com.kodekonveyor.authentication.RoleEntityTestData;
import com.kodekonveyor.authentication.RoleTestData;

public class RoleEntityRepositoryStubs {

  public static void
      behaviour(final RoleEntityRepository roleEntityRepository) {
    doReturn(Optional.of(RoleEntityTestData.get())).when(roleEntityRepository)
        .findById(RoleTestData.ID);
    doReturn(Optional.of(RoleEntityTestData.getNameCanbepaid()))
        .when(roleEntityRepository)
        .findById(RoleTestData.ID_CAN_BE_PAID);
    doReturn(Optional.of(RoleEntityTestData.getNameProjectManager()))
        .when(roleEntityRepository)
        .findById(RoleTestData.ID_PROJECT_MANAGER);
    doReturn(Optional.of(RoleEntityTestData.getRoleKodekonveyorContract()))
        .when(roleEntityRepository)
        .findById(RoleTestData.ID_KODEKONVEYOR_CONTRACT);

  }

}
