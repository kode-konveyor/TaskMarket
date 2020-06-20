package com.kodekonveyor.market.technical;

import com.jayway.jsonpath.DocumentContext;
import com.kodekonveyor.market.ValidationException;
import com.kodekonveyor.market.tasks.TaskEntity;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

import static com.kodekonveyor.logging.LoggingMarkerConstants.GITHUB;
import static com.kodekonveyor.market.technical.GithubConstants.PR_FOR_ISSUE_QUERY_HAS_NXT_PAGE_PATH;
import static com.kodekonveyor.market.technical.GithubConstants.PR_FOR_ISSUE_QUERY_PAGE_COUNT_PATH;
import static com.kodekonveyor.market.technical.GithubConstants.PR_FOR_ISSUE_QUERY_PATH;
import static com.kodekonveyor.market.technical.TechnicalConstants.LOG_GET_PR_FOR_ISSUE_FAILURE;
import static com.kodekonveyor.market.technical.TechnicalConstants.PR_NOT_FOUND_FOR_ISSUE;
import static org.apache.commons.lang3.BooleanUtils.isTrue;

@Service
public class GetPullRequestForIssueService {

    @Autowired
    private GithubGraphqlService githubGraphqlService;

    @Autowired
    private Logger loggerService;

    public List<Long> call(final TaskEntity task) {
        loggerService.info(GITHUB, TechnicalConstants.LOG_GET_PR_FOR_ISSUE_CALL);

        List<Long> prForIssueFound = fetchPRForIssue(task.getGithubId());

        if (CollectionUtils.isEmpty(prForIssueFound)) {
            loggerService.warn(GITHUB, LOG_GET_PR_FOR_ISSUE_FAILURE, task.getGithubId(), PR_NOT_FOUND_FOR_ISSUE);
            throw new ValidationException(PR_NOT_FOUND_FOR_ISSUE);
        }
        loggerService.debug(GITHUB, TechnicalConstants.LOG_GET_PR_FOR_ISSUE_SUCCESS, task.getGithubId());
        return prForIssueFound;
    }

    private List<Long> fetchPRForIssue(final Long taskId) {
        String query = GithubGraphqlQueryUtil.getGetPrForIssueQuery(
                GithubConstants.KODE_KONVEYOR,
                GithubConstants.TASK_MARKET,
                taskId
        );
        DocumentContext gqlResponse = githubGraphqlService.call(query);
        Long totalCount = JsonUtil.readPath(gqlResponse, PR_FOR_ISSUE_QUERY_PAGE_COUNT_PATH, Long.class);

        if (totalCount == null || totalCount == 0) {
            return Collections.emptyList();
        }

        final List<Long> allPRFound = JsonUtil.readPathAsList(gqlResponse, PR_FOR_ISSUE_QUERY_PATH, Long.class);

        Boolean hasNextPage = JsonUtil.readPath(
                gqlResponse, PR_FOR_ISSUE_QUERY_HAS_NXT_PAGE_PATH, Boolean.class
        );
        while (isTrue(hasNextPage)) {
            String endCursor = JsonUtil.readPath(
                    gqlResponse, GithubConstants.PR_FOR_ISSUE_QUERY_END_CURSOR_PATH, String.class
            );
            query = GithubGraphqlQueryUtil.getGetPrForIssueQuery(
                    GithubConstants.KODE_KONVEYOR,
                    GithubConstants.TASK_MARKET,
                    taskId,
                    endCursor
            );
            gqlResponse = githubGraphqlService.call(query);
            allPRFound.addAll(
                    JsonUtil.readPathAsList(gqlResponse, PR_FOR_ISSUE_QUERY_PATH, Long.class)
            );
            hasNextPage = JsonUtil.readPath(gqlResponse, PR_FOR_ISSUE_QUERY_HAS_NXT_PAGE_PATH, Boolean.class);
        }
        return allPRFound;
    }
}
