package com.kodekonveyor.market.technical;

public class GithubGraphqlQueryConstants {

    public static final String GET_PR_FOR_ISSUE_QUERY = "{" +
            "  repository(owner: \"%s\", name: \"%s\") {" +
            "    issue(number: %d) {" +
            "      id" +
            "      number" +
            "      title" +
            "      timelineItems(first: 1, itemTypes: [CONNECTED_EVENT]) {" +
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
}
