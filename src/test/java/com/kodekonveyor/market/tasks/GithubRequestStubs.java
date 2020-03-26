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
            .getResourceAsStream("/all_issues.json")
    ) {

      final String textJSON = IOUtils.toString(
          resourceAsStream,
          "UTF-8"
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
