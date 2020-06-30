package com.kodekonveyor.market.tasks;

import static org.mockito.Mockito.doReturn;

import org.json.JSONException;

public class UpdateTasksServiceStubs {

  public static void
      behaviour(final UpdateTasksService updateTasksService) throws JSONException {
    doReturn(TaskEntityDescriptionsTestData.getDescriptionDifferent())
        .when(updateTasksService)
        .call(TaskEntityTestData.get());

  }
}
