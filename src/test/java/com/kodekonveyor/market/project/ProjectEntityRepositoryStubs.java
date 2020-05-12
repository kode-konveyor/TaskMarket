package com.kodekonveyor.market.project;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.reset;

import java.util.List;
import java.util.Optional;

import com.kodekonveyor.authentication.RoleEntityTestData;

public class ProjectEntityRepositoryStubs {

  public static void
      behaviour(final ProjectEntityRepository projectEntityRepository) {
    doReturn(Optional.of(ProjectEntityTestData.get()))
        .when(projectEntityRepository).findById(ProjectTestData.ID);
    reset(projectEntityRepository);
    doReturn(List.of(ProjectEntityTestData.get())).when(projectEntityRepository)
        .findAll();
    doReturn(Optional.of(ProjectEntityTestData.getNullIdWithoutMilestone()))
        .when(projectEntityRepository)
        .findByName(ProjectTestData.NAME);
    doReturn(Optional.of(ProjectEntityTestData.getNameKodeKonveyor()))
        .when(projectEntityRepository)
        .findByName(ProjectTestData.NAME_KODE_KONVEYOR);
    doReturn(Optional.of(ProjectEntityTestData.getNameKodeKonveyor()))
        .when(projectEntityRepository).findByRole(RoleEntityTestData.getRoleKodekonveyorContract());
  }

  public static void
      behaviour2(final ProjectEntityRepository projectEntityRepository) {
    doReturn(Optional.of(ProjectEntityTestData.getUrlAndPullRequest()))
        .when(projectEntityRepository).findById(ProjectTestData.ID_ADD_FUNDS);
  }
}
