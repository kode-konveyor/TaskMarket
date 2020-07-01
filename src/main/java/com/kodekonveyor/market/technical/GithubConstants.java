package com.kodekonveyor.market.technical;

public class GithubConstants {

  public static final String AUTHORIZATION = "Authorization";
  public static final String CANNOT_CONNECT_TO_GITHUB =
      "cannot connect to github";
  public static final String GITHUB_GET_CALL_FAILURE =
          "Failure with status %s, while making get call to github.";
  public static final String CANNOT_CONVERT_TO_REQUIED_VALUE_TYPE =
          "Unable to convert response to the given value type.";
  public static final String CLOSE = "close";
  public static final String GET = "GET";
  public static final String GITHUB_API_URL_BASE = "https://api.github.com";
  public static final String GITHUB_CALL = "githubCall";
  public static final String ID = "id";
  public static final String IN_PROGRESS = "in progress";
  public static final String INTERNAL_ERROR = "internal error";
  public static final String ISSUES = "/issues";
  public static final String LABELS = "labels";
  public static final String LOGIN = "login";
  public static final String NAME = "name";
  public static final String TITLE_TEMPLATE = "%s/%s";
  public static final String OPEN = "open";
  public static final String REPO_NAME = "kode-konveyor/TaskMarket";
  public static final String REPOS = "/repos/";
  public static final String TITLE = "title";
  public static final String ASSIGNEES = "assignees";
  public static final String UP_FOR_GRAB = "up for grab";
  public static final String USER = "user";
  public static String GITHUB_GRAPHQL_ENDPOINT =
      "https://api.github.com/graphql";
  public static final String UPDATE_ISSUE_PATH_TEMPLATE = "/repos/kode-konveyor/TaskMarket/issues/%d";
  public static final String UTF_8 = "utf-8";
  public static final String BEARER = "bearer %s";
  public static final String APPLICATION_JSON_UTF_8 = "application/json; utf-8";
  public static final String CONTENT_TYPE = "Content-Type";
  public static final String POST = "POST";
  public static final String QUERY = "query";

  public static final String BODY = "body";
  public static final String NUMBER = "number";
  public static final String MILESTONE = "milestone";
  public static final String FRONSTSLASH = "/";

  public static final String PULL_REQUEST_QUERY = " {\n" +
      "  repository(owner: \"%s\", name: \"%s\") {" +
      "    pullRequest(number: %s) {" +
      "      comments(last: 1) {" +
      "        nodes {" +
      "          updatedAt" +
      "        }" +
      "      }" +
      "      reviews(last: 1) {" +
      "        nodes {" +
      "          updatedAt" +
      "        }" +
      "      }" +
      "      commits(last: 1) {" +
      "        nodes {" +
      "          commit {" +
      "            status {" +
      "              state," +
      "            }" +
      "            pushedDate" +
      "          }" +
      "        }" +
      "      }" +
      "    }" +
      "  }" +
      "}";
  public static final String COMMIT_STATUS_QUERY =
      "$['data']['repository']['pullRequest']['commits']['nodes'][0]['commit']['status']['state']";
  public static final String LAST_COMMIT_DATE_QUERY =
      "$['data']['repository']['pullRequest']['commits']['nodes'][0]['commit']['pushedDate']";
  public static final String LAST_REVIEW_DATE_QUERY =
      "$['data']['repository']['pullRequest']['reviews']['nodes'][0]['updatedAt']";
  public static final String LAST_COMMENT_DATE_QUERY =
      "$['data']['repository']['pullRequest']['comments']['nodes'][0]['updatedAt']";
  public static final String PR_FOR_ISSUE_QUERY_PATH =
          "$['data']['repository']['issue']['timelineItems']['nodes'][*]['subject']['number']";
  public static final String PR_FOR_ISSUE_QUERY_PAGE_COUNT_PATH =
          "$['data']['repository']['issue']['timelineItems']['totalCount']";
  public static final String PR_FOR_ISSUE_QUERY_END_CURSOR_PATH =
          "$['data']['repository']['issue']['timelineItems']['pageInfo']['endCursor']";
  public static final String PR_FOR_ISSUE_QUERY_HAS_NXT_PAGE_PATH =
          "$['data']['repository']['issue']['timelineItems']['pageInfo']['hasNextPage']";
  public static final long DAILY = 246_060 * 1000;

  public static final String TASK_MARKET = "TaskMarket";
  public static final String KODE_KONVEYOR = "kode-konveyor";
  public static final Object FAILURE = "FAILURE";
  public static final Object SUCCESS = "SUCCESS";
  public static final String FAILED_TO_CONVERT_TO_JSON = "Unable to convert to json string.";
  public static final String GITHUB_API_CALL_FAILURE = "Failure with status %s, while making call to github.";

}
