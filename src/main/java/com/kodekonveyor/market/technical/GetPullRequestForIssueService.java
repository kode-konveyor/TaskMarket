package com.kodekonveyor.market.technical;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import com.kodekonveyor.market.ValidationException;
import com.kodekonveyor.market.tasks.TaskEntity;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.kodekonveyor.logging.LoggingMarkerConstants.GITHUB;
import static com.kodekonveyor.market.technical.GithubConstants.PR_FOR_ISSUE_QUERY_PATH;
import static com.kodekonveyor.market.technical.GithubGraphqlQueryConstants.GET_PR_FOR_ISSUE_QUERY;
import static com.kodekonveyor.market.technical.TechnicalConstants.LOG_GET_PR_FOR_ISSUE_FAILURE;
import static com.kodekonveyor.market.technical.TechnicalConstants.PR_NOT_FOUND_FOR_ISSUE;

@Service
public class GetPullRequestForIssueService {

    @Autowired
    private GithubGraphqlService githubGraphqlService;

    @Autowired
    private Logger loggerService;

    public Long call(final TaskEntity task) {
        loggerService.info(GITHUB, TechnicalConstants.LOG_GET_PR_FOR_ISSUE_CALL);

        final String query = String.format(
                GET_PR_FOR_ISSUE_QUERY,
                GithubConstants.KODE_KONVEYOR,
                GithubConstants.TASK_MARKET,
                task.getGithubId()
        );
        final DocumentContext gqlResponse = githubGraphqlService.call(query);
        final Long pullRequestFound = readPRNumber(gqlResponse);

        if (Objects.isNull(pullRequestFound)) {
            loggerService.warn(GITHUB, LOG_GET_PR_FOR_ISSUE_FAILURE, task.getGithubId(), PR_NOT_FOUND_FOR_ISSUE);
            throw new ValidationException(PR_NOT_FOUND_FOR_ISSUE);
        }

        loggerService.debug(GITHUB, TechnicalConstants.LOG_GET_PR_FOR_ISSUE_SUCCESS, task.getGithubId());
        return pullRequestFound;
    }

    private Long readPRNumber(final DocumentContext gqlResponse) {
        return JsonPath.parse(
                gqlResponse.jsonString(),
                Configuration.builder()
                        .options(Option.SUPPRESS_EXCEPTIONS)
                        .build()
        )
                .read(PR_FOR_ISSUE_QUERY_PATH, Long.class);
    }
}
