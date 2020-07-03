package com.kodekonveyor.market.technical;

public class GithubGraphqlQueryUtil {

    private static final String GET_PR_FOR_ISSUE_QUERY_FORMAT = "{" +
            "  repository(owner: \"%s\", name: \"%s\") {" +
            "    issue(number: %d) {" +
            "      id" +
            "      number" +
            "      title" +
            "      timelineItems(first: 250, after: %s, itemTypes: [CONNECTED_EVENT]) {" +
            "       pageCount" +
            "       pageInfo {" +
            "          endCursor" +
            "          hasNextPage" +
            "        }" +
            "        totalCount" +
            "        nodes {" +
            "          __typename" +
            "          ... on ConnectedEvent {" +
            "            subject {" +
            "              __typename" +
            "              ... on PullRequest {" +
            "                number" +
            "              }" +
            "            }" +
            "          }" +
            "        }" +
            "      }" +
            "    }" +
            "  }" +
            "}";

    private static final String STRING_WITH_QUOTES_FORMAT = "\"%s\"";

    public static String getGetPrForIssueQuery(final String gitHubOwner,
                                               final String repoName,
                                               final Long taskId) {
        return String.format(
                GET_PR_FOR_ISSUE_QUERY_FORMAT,
                gitHubOwner,
                repoName,
                taskId,
                null
        );
    }

    public static String getGetPrForIssueQuery(final String gitHubOwner,
                                               final String repoName,
                                               final Long taskId,
                                               final String cursor) {
        return String.format(
                GET_PR_FOR_ISSUE_QUERY_FORMAT,
                gitHubOwner,
                repoName,
                taskId,
                String.format(STRING_WITH_QUOTES_FORMAT, cursor)
        );
    }
}
