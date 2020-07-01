package com.kodekonveyor.technical;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.assertj.core.util.Lists;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

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

  public static final String MESSAGE =
      "Up for grab tasks below minimum for grab for project : kode-konveyor/TaskMarket . Current 'up for grab' tasks count : 1 , the minimum count of 'up for grab' tasks required : 2";

  public static final Long TEST_ISSUE_ID = 66L;
  public static final List<Long> TEST_SINGLE_PR_ID = Lists.newArrayList(56L);
  public static final List<Long> TEST_MULTIPLE_PR_ID =
      Lists.newArrayList(56L, 57L);
  public static final String EXPECTED_MSG_WHN_PR_NOT_FOUND_FOR_ISSUE =
      "Pull request not found for issue.";
  public static final String EXP_LOG_GET_PR_FOR_ISSUE_CALL =
      "Execution to fetch pull request starts.";
  public static final String EXP_LOG_GET_PR_FOR_ISSUE_SUCCESS =
      "Successfully fetched linked pull request for issue : {}";
  public static final String EXP_LOG_GET_PR_FOR_ISSUE_FAILURE =
      "Failed to fetched pull request for issue {}, error : {}";
  public static final Marker EXP_GITHUB_MARKER =
      MarkerFactory.getMarker("github");
  public static final String MESSAGE1 =
      "Up for grab tasks below minimum for grab for project : kode-konveyor/TaskMarket . Current 'up for grab' tasks count : 2 , the minimum count of 'up for grab' tasks required : 2";
  public static final String MESSAGE2 =
      "Up for grab tasks below minimum for grab for project : kode-konveyor/TaskMarket . Current 'up for grab' tasks count : 0 , the minimum count of 'up for grab' tasks required : 2";
}
