package com.kodekonveyor.technical;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class TechnicalTestData {

  public static final String STATUS_STATE_FAILURE =
      ",\"status\":{\"state\":\"FAILURE\"}}}]}}}}}";
  public static final String STATUS_STATE_SUCCESS =
      ",\"status\":{\"state\":\"SUCCESS\"}}}]}}}}}";
  public static final String COMMITS_NODES_COMMIT_PUSHED_DATE =
      "}]},\"commits\":{\"nodes\":[{\"commit\":{\"pushedDate\":";
  public static final String PULL_REQUEST_COMMENT =
      "{\"data\":{\"repository\":{\"pullRequest\":{\"comments\":{\"nodes\":[{\"updatedAt\":\"2020-05-09T06:20:19Z\"}]},\"reviews\":{\"nodes\":[{\"updatedAt\":";
  public static final String SUCCESS = "SUCCESS";
  public static final String LAST_COMMIT_DATE = "2020-05-09T06:13:51Z";
  public static final String LAST_REVIEW_DATE = "2020-05-09T06:19:25Z";
  public static final String LAST_COMMENT_DATE = "2020-05-09T06:20:19Z";
  public static final String TASK_MARKET = "TaskMarket";
  public static final String KODE_KONVEYOR = "kode-konveyor";
  public static final String PULL_REQUEST_ANSWER =
      "{\"data\":{\"repository\":{\"pullRequest\":{\"comments\":{\"nodes\":[{\"updatedAt\":\"2020-05-09T06:20:19Z\"}]},\"reviews\":{\"nodes\":[{\"updatedAt\":\"2020-05-09T06:19:25Z\"}]},\"commits\":{\"nodes\":[{\"commit\":{\"pushedDate\":\"2020-05-09T06:13:51Z\",\"status\":{\"state\":\"SUCCESS\"}}}]}}}}}";

  public static final String REPOSITORY_ID_QUERY =
      "$['data']['repository']['id']";

  public static final String REPOSITORY_ID =
      "MDEwOlJlcG9zaXRvcnkxNzgyNDgwMzA=";
  public static final String QUERY = " {\n" +
      "  repository(owner: \"%s\", name: \"%s\") {" +
      "        id" +
      "  }" +
      "}";
  public static final String SUCCESSFULL_PULL_REQUEST_UNDER_THREE_DAYS_ANSWER =
      PULL_REQUEST_COMMENT +
          '"' + Instant.now().minus(2, ChronoUnit.DAYS) + '"' + COMMITS_NODES_COMMIT_PUSHED_DATE + '"' + Instant.now().minus(2, ChronoUnit.DAYS) + '"' + STATUS_STATE_SUCCESS;

  public static final String SUCCESSFULL_REVIEW_AND_COMMIT_UNDER_THREE_DAYS_ANSWER =
      PULL_REQUEST_COMMENT +
          '"' + Instant.now().minus(4, ChronoUnit.DAYS) + '"' + COMMITS_NODES_COMMIT_PUSHED_DATE + '"' + Instant.now().minus(2, ChronoUnit.DAYS) + '"' + STATUS_STATE_SUCCESS;
  public static final String SUCCESSFULL_REVIEW_AND_COMMIT_EQUAL_TO_FOUR_DAYS_ANSWER =
      PULL_REQUEST_COMMENT +
          '"' + Instant.now().minus(6, ChronoUnit.DAYS) + '"' + COMMITS_NODES_COMMIT_PUSHED_DATE + '"' + Instant.now().minus(2, ChronoUnit.DAYS) + '"' + STATUS_STATE_SUCCESS;
  public static final String SUCCESSFULL_REVIEW_AND_COMMIT_MORE_THAN_THREE_DAYS_ANSWER =
      PULL_REQUEST_COMMENT +
          '"' + Instant.now().minus(8, ChronoUnit.DAYS) + '"' + COMMITS_NODES_COMMIT_PUSHED_DATE + '"' + Instant.now().minus(2, ChronoUnit.DAYS) + '"' + STATUS_STATE_SUCCESS;
  public static final String FAILURE_COMMIT_MORE_THAN_THREE_DAYS_ANSWER =
      PULL_REQUEST_COMMENT +
          '"' + Instant.now().minus(8, ChronoUnit.DAYS) + '"' + COMMITS_NODES_COMMIT_PUSHED_DATE + '"' + Instant.now().minus(6, ChronoUnit.DAYS) + '"' + STATUS_STATE_FAILURE;
  public static final String FAILURE_COMMIT_EQUAL_TO_FOUR_DAYS_ANSWER =
      PULL_REQUEST_COMMENT +
          '"' + Instant.now().minus(8, ChronoUnit.DAYS) + '"' + COMMITS_NODES_COMMIT_PUSHED_DATE + '"' + Instant.now().minus(4, ChronoUnit.DAYS) + '"' + STATUS_STATE_FAILURE;
  public static final String FAILURE_COMMIT_LESS_THAN_THREE_DAYS_ANSWER =
      PULL_REQUEST_COMMENT +
          '"' + Instant.now().minus(8, ChronoUnit.DAYS) + '"' + COMMITS_NODES_COMMIT_PUSHED_DATE + '"' + Instant.now().minus(2, ChronoUnit.DAYS) + '"' + STATUS_STATE_FAILURE;

}
