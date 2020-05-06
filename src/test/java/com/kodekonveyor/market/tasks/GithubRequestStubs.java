package com.kodekonveyor.market.tasks;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.assertj.core.api.Assertions;
import org.json.JSONArray;
import org.json.JSONException;
import org.mockito.Mockito;

public class GithubRequestStubs {

  public static void behaviour(final GithubCallService githubRequest) {
    JSONArray array = null;
    try (
        InputStream resourceAsStream = GithubRequestStubs.class
            .getResourceAsStream(
                GetRepositoryTasksServiceTestData.ALL_ISSUES_JSON
            )
    ) {

      final String textJSON = IOUtils.toString(
          resourceAsStream,
          GetRepositoryTasksServiceTestData.UTF_8
      );
      array = new JSONArray(textJSON);

    } catch (IOException | JSONException exception) {
      Assertions.fail(exception.getMessage());
      return;
    }

    Mockito.doReturn(array).when(
        githubRequest
    ).call(GetRepositoryTasksServiceTestData.REPO_NAME);

  }

}
