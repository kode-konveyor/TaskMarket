package com.kodekonveyor.market.tasks;

import java.util.List;

import org.json.JSONException;
import org.mockito.Mockito;

public class GetRepositoryTasksServiceStubs {

  public static void behaviour(
      final GetRepositoryTasksService getRepositoryTasksService
  ) throws JSONException {

    Mockito.doReturn(List.of(TaskDToTestData.get())).when(
        getRepositoryTasksService
    ).call(GetRepositoryTasksServiceTestData.REPO_NAME);

  }

}
