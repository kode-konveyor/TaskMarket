package com.kodekonveyor.technical;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.kodekonveyor.market.technical.GithubGraphqlQueryConstants;
import com.kodekonveyor.market.technical.GithubGraphqlService;
import org.mockito.Mockito;

import static com.kodekonveyor.market.technical.GithubConstants.KODE_KONVEYOR;
import static com.kodekonveyor.market.technical.GithubConstants.TASK_MARKET;

public class GetPullRequestForIssueServiceStubs {

    private static final String query = String
            .format(
                    GithubGraphqlQueryConstants.GET_PR_FOR_ISSUE_QUERY, KODE_KONVEYOR,
                    TASK_MARKET, TechnicalTestData.TEST_ISSUE_ID
            );

    private static final String GET_PR_GQL_RESPONSE_FORMAT = "{" +
            "  \"data\": {" +
            "    \"repository\": {" +
            "      \"issue\": {" +
            "        \"id\": \"MDU6SXNzdWU1Nzc0NjU0OTk=\"," +
            "        \"number\": 66," +
            "        \"title\": \"TestService/TestBehaviour\"," +
            "        \"timelineItems\": {" +
            "          \"nodes\": %s" +
            "        }" +
            "      }" +
            "    }" +
            "  }" +
            "}";

    private static final DocumentContext GET_PR_GQL_RESPONSE = JsonPath.parse(
            String.format(
                    GET_PR_GQL_RESPONSE_FORMAT,
                    " [" +
                            " [           {" +
                            "              \"__typename\": \"ConnectedEvent\"," +
                            "              \"subject\": {" +
                            "                \"__typename\": \"PullRequest\"," +
                            "                \"number\": 56" +
                            "              }" +
                            "            }" +
                            "          ]"
            )
    );

    private static final DocumentContext GET_PR_GQL_RESPONSE_WHEN_PR_NOT_LINKED = JsonPath.parse(
            String.format(GET_PR_GQL_RESPONSE_FORMAT, " [ ]")
    );

    private static final DocumentContext GET_PR_GQL_ERROR_RESPONSE = JsonPath.parse(
            "{" +
                    "  \"data\": {" +
                    "    \"repository\": {" +
                    "      \"issue\": null" +
                    "    }" +
                    "  }," +
                    "  \"errors\": [" +
                    "    {" +
                    "      \"type\": \"NOT_FOUND\"," +
                    "      \"path\": [" +
                    "        \"repository\"," +
                    "        \"issue\"" +
                    "      ]," +
                    "      \"locations\": [" +
                    "        {" +
                    "          \"line\": 3," +
                    "          \"column\": 5" +
                    "        }" +
                    "      ]," +
                    "      \"message\": \"Could not resolve to an Issue with the number of 999.\"" +
                    "    }" +
                    "  ]" +
                    "}"
    );

    public static final void mockSuccessResponse(final GithubGraphqlService githubGraphqlService) {
        Mockito.when(githubGraphqlService.call(query)).thenReturn(GET_PR_GQL_RESPONSE);
    }

    public static final void mockWhenNotLinked(final GithubGraphqlService githubGraphqlService) {
        Mockito.when(githubGraphqlService.call(query)).thenReturn(GET_PR_GQL_RESPONSE_WHEN_PR_NOT_LINKED);
    }

    public static final void mockWhenIssueNotFound(final GithubGraphqlService githubGraphqlService) {
        Mockito.when(githubGraphqlService.call(query)).thenReturn(GET_PR_GQL_ERROR_RESPONSE);
    }
}
