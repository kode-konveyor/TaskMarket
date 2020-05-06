package com.kodekonveyor.market.tasks;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.reset;

import java.util.Optional;

import com.kodekonveyor.authentication.UserEntityTestData;
import com.kodekonveyor.market.register.MarketUserEntityRepository;
import com.kodekonveyor.market.register.MarketUserEntityTestData;

public class TaskEntityStubs {

  public static void behaviour(
      final TaskEntityRepository taskRepository,
      final MarketUserEntityRepository marketUserEntityRepository
  ) {
    reset(taskRepository);
    doReturn(TaskEntityTestData.listStatusInProgress()).when(taskRepository)
        .findByStatusAndResponsible(
            TaskStatusEnum.IN_PROGRESS, MarketUserEntityTestData.get()
        );
    doReturn(TaskEntityTestData.listStatusDone()).when(taskRepository)
        .findByStatusAndResponsible(
            TaskStatusEnum.DONE, MarketUserEntityTestData.get()
        );
    doReturn(TaskEntityTestData.listIsPublicFalse()).when(taskRepository)
        .findByStatusAndResponsibleAndProjectIsPublic(
            TaskStatusEnum.UP_FOR_GRAB,
            MarketUserEntityTestData.get(), false
        );
    doReturn(TaskEntityTestData.listIsPublicTrue()).when(taskRepository)
        .findByStatusAndProjectIsPublic(TaskStatusEnum.UP_FOR_GRAB, true);
    doReturn(Optional.of(TaskEntityTestData.get())).when(taskRepository)
        .findByServiceAndBehaviour(
            TaskEntityTestData.get().getService(),
            TaskEntityTestData.get().getBehaviour()
        );
    doReturn(Optional.of(MarketUserEntityTestData.getRoleProjectManager()))
        .when(marketUserEntityRepository)
        .findByUser(UserEntityTestData.getRoleProjectManager());
  }
}
