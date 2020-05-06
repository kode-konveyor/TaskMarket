package com.kodekonveyor.market.tasks;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.assertj.core.api.Assertions;
import org.json.JSONArray;

public class GetRepositoryTasksServiceTestData {

  public static final String ALL_ISSUES_JSON = "/all_issues.json";
  public static final String UTF_8 = "UTF-8";
  public static final JSONArray ARRAY = new JSONArray();
  public static final String ID = "id";
  public static final int INDEX = 0;
  public static final int INDEX2 = 2;
  public static final int INDEX3 = 15;
  public static final int INDEX4 = 1;

  public static final String LOGIN = "login";
  public static final String REPO_NAME = "kode-konveyor/TaskMarket";
  public static final Integer TASK_COUNT = 28;
  public static final String TASK_ID = "579047644";
  public static final String TASK_ID2 = "528890650";
  public static final String TASK_NAME =
      "RegistrationController/store username";
  public static final String TASK_NAME2 = "ProjectEntity/Missing Public Field";
  public static final String TASK_OWNER = "magwas";
  public static final String TASK_OWNER2 = "pthakkar02";
  public static final String TITLE = "title";
  public static final String USER = "user";

  public static String get() {

    String textJSON = null;
    try (
        InputStream resourceAsStream = GithubRequestStubs.class
            .getResourceAsStream(ALL_ISSUES_JSON)
    ) {
      textJSON = IOUtils.toString(
          resourceAsStream,
          UTF_8
      );
    } catch (final IOException exception) {
      Assertions.fail(exception.getMessage());
    }
    return textJSON;
  }

}
