package com.kodekonveyor.market.project;

import com.kodekonveyor.authentication.RoleEntityTestData;
import org.mockito.ArgumentCaptor;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.reset;

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
    doReturn(Optional.of(ProjectEntityTestData.getUrlAndPullRequest()))
        .when(projectEntityRepository)
        .findByName(ProjectTestData.PROJECT_NAME);
    doReturn(Optional.of(ProjectEntityTestData.getNameKodeKonveyor()))
        .when(projectEntityRepository).findByRole(RoleEntityTestData.getRoleKodekonveyorContract());
    doReturn(Optional.of(ProjectEntityTestData.getUrlAndPullRequest()))
        .when(projectEntityRepository).findByMilestone(MilestoneEntityTestData.get());
    doReturn(Optional.of(ProjectEntityTestData.get()))
        .when(projectEntityRepository).findByMilestone(MilestoneEntityTestData.getOtherMilestone());
    doReturn(List.of(ProjectEntityTestData.getPrivateProject()))
        .when(projectEntityRepository).findByIsPublic(false);

    doReturn(List.of(ProjectEntityTestData.getPublicProject()))
        .when(projectEntityRepository).findByIsPublic(true);
    doAnswer(invocationOnMock -> invocationOnMock.getArgument(0))
            .when(projectEntityRepository).save(any(ProjectEntity.class));
  }

  public static void
      behaviour2(final ProjectEntityRepository projectEntityRepository) {
    doReturn(Optional.of(ProjectEntityTestData.getAddFunds()))
        .when(projectEntityRepository).findById(ProjectTestData.ID_ADD_FUNDS);
    doReturn(Optional.of(ProjectEntityTestData.getManagerRole()))
        .when(projectEntityRepository).findById(ProjectTestData.ID_BUDGET);
    doAnswer(invocationOnMock -> invocationOnMock.getArgument(0))
            .when(projectEntityRepository).save(any(ProjectEntity.class));
  }

  public static void mockSaveAndCapture(final ProjectEntityRepository repository, final ArgumentCaptor<ProjectEntity> argumentCaptor) {
    doAnswer(invocationOnMock -> invocationOnMock.getArgument(0))
            .when(repository).save(argumentCaptor.capture());

  }

}
