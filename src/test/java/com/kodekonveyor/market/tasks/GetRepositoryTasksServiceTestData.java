package com.kodekonveyor.market.tasks;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.assertj.core.api.Assertions;
import org.json.JSONArray;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

public class GetRepositoryTasksServiceTestData {

  public static final String ALL_ISSUES_JSON = "/all_issues.json";
  public static final String UTF_8 = "UTF-8";
  public static final JSONArray ARRAY = new JSONArray();
  public static final String ID = "id";
  public static final int INDEX = 0;

  public static final String LOGIN = "login";
  public static final String REPO_NAME = "kode-konveyor/TaskMarket";
  public static final String TASK_ID = "1197";
  public static final String TASK_NAME =
      "RegistrationController/store username";
  public static final String BEHAVIOUR = "Get tasks from github";
  public static final String TITLE = "title";
  public static final String USER = "user";
  public static final String BODY = "body";
  public static final String DESCRIPTION =
      "description comes here";
  public static final String NUMBER = "number";
  public static final String GITHUB_ID = "66";
  public static final String SERVICE = "UpdateTasksController";
  public static final String FRONTSLASH = "/";
  public static final int ZERO = 0;
  public static final int ONE = 1;
  public static final String MARKET_USER_ID = "1821";
  public static final String TASK_RECEIVED = "Tasks received for repo : ";

  public static final String LABELS = "labels";
  public static final String NAME = "name";
  public static final String UP_FOR_GRAB = "up for grab";
  public static final Object OPEN = "open";
  public static final int INDEX1 = 1;

  public static final Marker TASK = MarkerFactory.getMarker("task");

  public static final String ENTITIES_SAVED_SUCCESSFULLY =
      "Task entities saved successfully Ids : ";
  public static final String TASK_ID1 = "1198";

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
