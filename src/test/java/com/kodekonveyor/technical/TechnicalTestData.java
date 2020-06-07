package com.kodekonveyor.technical;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class TechnicalTestData {

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
      "{\"data\":{\"repository\":{\"pullRequest\":{\"comments\":{\"nodes\":[{\"updatedAt\":\"2020-05-09T06:20:19Z\"}]},\"reviews\":{\"nodes\":[{\"updatedAt\":" +
          '"' + Instant.now().minus(2, ChronoUnit.DAYS) + '"' + "}]},\"commits\":{\"nodes\":[{\"commit\":{\"pushedDate\":" + '"' + Instant.now().minus(2, ChronoUnit.DAYS) + '"' + ",\"status\":{\"state\":\"SUCCESS\"}}}]}}}}}";

  public static final String SUCCESSFULL_REVIEW_AND_COMMIT_UNDER_THREE_DAYS_ANSWER =
      "{\"data\":{\"repository\":{\"pullRequest\":{\"comments\":{\"nodes\":[{\"updatedAt\":\"2020-05-09T06:20:19Z\"}]},\"reviews\":{\"nodes\":[{\"updatedAt\":" +
          '"' + Instant.now().minus(4, ChronoUnit.DAYS) + '"' + "}]},\"commits\":{\"nodes\":[{\"commit\":{\"pushedDate\":" + '"' + Instant.now().minus(2, ChronoUnit.DAYS) + '"' + ",\"status\":{\"state\":\"SUCCESS\"}}}]}}}}}";
  public static final String SUCCESSFULL_REVIEW_AND_COMMIT_EQUAL_TO_FOUR_DAYS_ANSWER =
      "{\"data\":{\"repository\":{\"pullRequest\":{\"comments\":{\"nodes\":[{\"updatedAt\":\"2020-05-09T06:20:19Z\"}]},\"reviews\":{\"nodes\":[{\"updatedAt\":" +
          '"' + Instant.now().minus(6, ChronoUnit.DAYS) + '"' + "}]},\"commits\":{\"nodes\":[{\"commit\":{\"pushedDate\":" + '"' + Instant.now().minus(2, ChronoUnit.DAYS) + '"' + ",\"status\":{\"state\":\"SUCCESS\"}}}]}}}}}";
  public static final String SUCCESSFULL_REVIEW_AND_COMMIT_MORE_THAN_THREE_DAYS_ANSWER =
      "{\"data\":{\"repository\":{\"pullRequest\":{\"comments\":{\"nodes\":[{\"updatedAt\":\"2020-05-09T06:20:19Z\"}]},\"reviews\":{\"nodes\":[{\"updatedAt\":" +
          '"' + Instant.now().minus(8, ChronoUnit.DAYS) + '"' + "}]},\"commits\":{\"nodes\":[{\"commit\":{\"pushedDate\":" + '"' + Instant.now().minus(2, ChronoUnit.DAYS) + '"' + ",\"status\":{\"state\":\"SUCCESS\"}}}]}}}}}";
  public static final String FAILURE_COMMIT_MORE_THAN_THREE_DAYS_ANSWER =
      "{\"data\":{\"repository\":{\"pullRequest\":{\"comments\":{\"nodes\":[{\"updatedAt\":\"2020-05-09T06:20:19Z\"}]},\"reviews\":{\"nodes\":[{\"updatedAt\":" +
          '"' + Instant.now().minus(8, ChronoUnit.DAYS) + '"' + "}]},\"commits\":{\"nodes\":[{\"commit\":{\"pushedDate\":" + '"' + Instant.now().minus(6, ChronoUnit.DAYS) + '"' + ",\"status\":{\"state\":\"FAILURE\"}}}]}}}}}";
  public static final String FAILURE_COMMIT_EQUAL_TO_FOUR_DAYS_ANSWER =
      "{\"data\":{\"repository\":{\"pullRequest\":{\"comments\":{\"nodes\":[{\"updatedAt\":\"2020-05-09T06:20:19Z\"}]},\"reviews\":{\"nodes\":[{\"updatedAt\":" +
          '"' + Instant.now().minus(8, ChronoUnit.DAYS) + '"' + "}]},\"commits\":{\"nodes\":[{\"commit\":{\"pushedDate\":" + '"' + Instant.now().minus(4, ChronoUnit.DAYS) + '"' + ",\"status\":{\"state\":\"FAILURE\"}}}]}}}}}";
  public static final String FAILURE_COMMIT_LESS_THAN_THREE_DAYS_ANSWER =
      "{\"data\":{\"repository\":{\"pullRequest\":{\"comments\":{\"nodes\":[{\"updatedAt\":\"2020-05-09T06:20:19Z\"}]},\"reviews\":{\"nodes\":[{\"updatedAt\":" +
          '"' + Instant.now().minus(8, ChronoUnit.DAYS) + '"' + "}]},\"commits\":{\"nodes\":[{\"commit\":{\"pushedDate\":" + '"' + Instant.now().minus(2, ChronoUnit.DAYS) + '"' + ",\"status\":{\"state\":\"FAILURE\"}}}]}}}}}";

}
