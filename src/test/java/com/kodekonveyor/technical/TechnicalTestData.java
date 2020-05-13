package com.kodekonveyor.technical;

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

}
