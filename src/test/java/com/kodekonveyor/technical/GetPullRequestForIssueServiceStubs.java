package com.kodekonveyor.technical;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.kodekonveyor.market.technical.GithubGraphqlQueryUtil;
import com.kodekonveyor.market.technical.GithubGraphqlService;
import org.mockito.Mockito;

import static com.kodekonveyor.market.technical.GithubConstants.KODE_KONVEYOR;
import static com.kodekonveyor.market.technical.GithubConstants.TASK_MARKET;

public class GetPullRequestForIssueServiceStubs {

    private static final String TEST_CURSOR = "cursor-01";

    private static final String query = GithubGraphqlQueryUtil.getGetPrForIssueQuery(
            KODE_KONVEYOR,
            TASK_MARKET,
            TechnicalTestData.TEST_ISSUE_ID
    );

    private static final String pagedQuery = GithubGraphqlQueryUtil.getGetPrForIssueQuery(
            KODE_KONVEYOR,
            TASK_MARKET,
            TechnicalTestData.TEST_ISSUE_ID,
            TEST_CURSOR
    );

    private static final String GET_PR_GQL_RESPONSE_FORMAT = "{" +
            "  \"data\": {" +
            "    \"repository\": {" +
            "      \"issue\": {" +
            "        \"id\": \"MDU6SXNzdWU1OTYwNTE4MDg=\"," +
            "        \"number\": 66," +
            "        \"title\": \"UpdateProjectModelController/roles\"," +
            "        \"timelineItems\": {" +
            "          \"pageCount\": %d," +
            "          \"pageInfo\": {" +
            "            \"endCursor\": \"cursor-01\"," +
            "            \"hasNextPage\": %b" +
            "          }," +
            "          \"filteredCount\": 1," +
            "          \"totalCount\": %d," +
            "          \"nodes\": %s" +
            "        }" +
            "      }" +
            "    }" +
            "  }" +
            "}";

    private static final String SINGLE_GQL_RES_FORMAT = " [" +
            "        {" +
            "              \"__typename\": \"ConnectedEvent\"," +
            "              \"subject\": {" +
            "                \"__typename\": \"PullRequest\"," +
            "                \"number\": %d" +
            "              }" +
            "            }" +
            "          ]";

    private static final DocumentContext GET_SINGLE_PR_GQL_RESPONSE = JsonPath.parse(
            String.format(
                    GET_PR_GQL_RESPONSE_FORMAT, 1, false, 1,
                    String.format(SINGLE_GQL_RES_FORMAT, 56)
            )
    );

    private static final DocumentContext GET_PR_GQL_RESPONSE_WHEN_PR_NOT_LINKED = JsonPath.parse(
            String.format(GET_PR_GQL_RESPONSE_FORMAT, 0, false, 0, " [ ]")
    );


    private static final DocumentContext GET_MULTIPLE_PR_GQL_RESPONSE_PAGE_1 = JsonPath.parse(
            String.format(
                    GET_PR_GQL_RESPONSE_FORMAT, 1, true, 2,
                    String.format(SINGLE_GQL_RES_FORMAT, 56)
            )
    );

    private static final DocumentContext GET_MULTIPLE_PR_GQL_RESPONSE_PAGE_2 = JsonPath.parse(
            String.format(
                    GET_PR_GQL_RESPONSE_FORMAT, 2, false, 2,
                    String.format(SINGLE_GQL_RES_FORMAT, 57)

            )
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
        Mockito.when(githubGraphqlService.call(query)).thenReturn(GET_SINGLE_PR_GQL_RESPONSE);
    }

    public static final void mockWhenNotLinked(final GithubGraphqlService githubGraphqlService) {
        Mockito.when(githubGraphqlService.call(query)).thenReturn(GET_PR_GQL_RESPONSE_WHEN_PR_NOT_LINKED);
    }

    public static final void mockWhenIssueNotFound(final GithubGraphqlService githubGraphqlService) {
        Mockito.when(githubGraphqlService.call(query)).thenReturn(GET_PR_GQL_ERROR_RESPONSE);
    }

    public static final void mockWhenMultipleIssuesFound(final GithubGraphqlService githubGraphqlService) {
        Mockito.when(githubGraphqlService.call(query)).thenReturn(GET_MULTIPLE_PR_GQL_RESPONSE_PAGE_1);
        Mockito.when(githubGraphqlService.call(pagedQuery)).thenReturn(GET_MULTIPLE_PR_GQL_RESPONSE_PAGE_2);
    }
}
