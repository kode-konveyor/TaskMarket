package com.kodekonveyor.market.technical;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.TypeRef;
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
import static com.kodekonveyor.market.technical.TechnicalConstants.MORE_THAN_ONE_PR_FOR_ISSUE;
import static com.kodekonveyor.market.technical.TechnicalConstants.PR_NOT_FOUND_FOR_ISSUE;
import static org.apache.commons.lang3.BooleanUtils.isTrue;

@Service
public class GetPullRequestForIssueService {

    @Autowired
    private GithubGraphqlService githubGraphqlService;

    @Autowired
    private Logger loggerService;

    public Long call(final TaskEntity task) {
        loggerService.info(GITHUB, TechnicalConstants.LOG_GET_PR_FOR_ISSUE_CALL);

        List<Long> prForIssueFound = fetchPRForIssue(task.getGithubId());

        if (CollectionUtils.isEmpty(prForIssueFound)) {
            loggerService.warn(GITHUB, LOG_GET_PR_FOR_ISSUE_FAILURE, task.getGithubId(), PR_NOT_FOUND_FOR_ISSUE);
            throw new ValidationException(PR_NOT_FOUND_FOR_ISSUE);
        }

        if (prForIssueFound.size() > 1) {
            loggerService.warn(GITHUB, LOG_GET_PR_FOR_ISSUE_FAILURE, task.getGithubId(), MORE_THAN_ONE_PR_FOR_ISSUE);
            throw new ValidationException(MORE_THAN_ONE_PR_FOR_ISSUE);
        }

        Long pullRequestFound = prForIssueFound.get(0);
        loggerService.debug(GITHUB, TechnicalConstants.LOG_GET_PR_FOR_ISSUE_SUCCESS, task.getGithubId());
        return pullRequestFound;
    }

    private List<Long> fetchPRForIssue(Long taskId) {
        String query = GithubGraphqlQueryUtil.getGetPrForIssueQuery(
                GithubConstants.KODE_KONVEYOR,
                GithubConstants.TASK_MARKET,
                taskId
        );
        DocumentContext gqlResponse = githubGraphqlService.call(query);
        Long totalCount = JsonUtil.readPath(
                gqlResponse, PR_FOR_ISSUE_QUERY_PAGE_COUNT_PATH, new TypeRef<>() {}
        );

        if (totalCount == null || totalCount == 0) {
            return Collections.emptyList();
        }

        final List<Long> allPRFound = JsonUtil.readPath(
                gqlResponse, PR_FOR_ISSUE_QUERY_PATH, new TypeRef<>() {}
        );

        Boolean hasNextPage = JsonUtil.readPath(
                gqlResponse, PR_FOR_ISSUE_QUERY_HAS_NXT_PAGE_PATH, new TypeRef<>() {}
        );
        while (isTrue(hasNextPage)) {
            String endCursor = JsonUtil.readPath(
                    gqlResponse, GithubConstants.PR_FOR_ISSUE_QUERY_END_CURSOR_PATH, new TypeRef<>() {}
            );
            query = GithubGraphqlQueryUtil.getGetPrForIssueQuery(
                    GithubConstants.KODE_KONVEYOR,
                    GithubConstants.TASK_MARKET,
                    taskId,
                    endCursor
            );

            gqlResponse = githubGraphqlService.call(query);
            allPRFound.addAll(
                    JsonUtil.readPath(gqlResponse, PR_FOR_ISSUE_QUERY_PATH, new TypeRef<>() {})
            );
            hasNextPage = JsonUtil.readPath(
                    gqlResponse, PR_FOR_ISSUE_QUERY_HAS_NXT_PAGE_PATH, new TypeRef<>() {}
            );
        }
        return allPRFound;
    }
}
