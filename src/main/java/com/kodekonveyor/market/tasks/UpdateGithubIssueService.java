package com.kodekonveyor.market.tasks;

import com.google.common.collect.ImmutableMap;
import com.kodekonveyor.logging.LoggingMarkerConstants;
import com.kodekonveyor.market.technical.GithubAPIExecutorService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static com.kodekonveyor.market.tasks.TaskConstants.LOG_UPDATE_GITHUB_ISSUE_SERVICE_CALL;
import static com.kodekonveyor.market.tasks.TaskConstants.LOG_UPDATE_GITHUB_ISSUE_SERVICE_FAILURE_CALL;
import static com.kodekonveyor.market.tasks.TaskConstants.LOG_UPDATE_GITHUB_ISSUE_SERVICE_SUCCESS_CALL;
import static com.kodekonveyor.market.technical.GithubConstants.ASSIGNEES;
import static com.kodekonveyor.market.technical.GithubConstants.BODY;
import static com.kodekonveyor.market.technical.GithubConstants.LABELS;
import static com.kodekonveyor.market.technical.GithubConstants.TITLE;
import static com.kodekonveyor.market.technical.GithubConstants.TITLE_TEMPLATE;
import static com.kodekonveyor.market.technical.GithubConstants.UPDATE_ISSUE_PATH_TEMPLATE;

@Service
public class UpdateGithubIssueService {

    @Autowired
    private GithubAPIExecutorService githubAPIExecutorService;

    @Autowired
    private Logger logger;

    public void call(final TaskEntity taskEntity) {
        logger.info(LoggingMarkerConstants.TASK, LOG_UPDATE_GITHUB_ISSUE_SERVICE_CALL, taskEntity.getId());

        Map<String, Object> request = ImmutableMap.<String, Object>builder()
                .put(BODY, taskEntity.getDescription())
                .put(TITLE, String.format(TITLE_TEMPLATE, taskEntity.getService(), taskEntity.getBehaviour()))
                .put(LABELS, Optional.ofNullable(taskEntity.getStatus())
                        .map(val -> Set.of(val.getValue()))
                        .orElse(Collections.emptySet())
                )
                .put(ASSIGNEES, Optional.ofNullable(taskEntity.getMarketUser())
                        .map(val -> Set.of(taskEntity.getMarketUser().getUser().getLogin()))
                        .orElse(Collections.emptySet())
                )
                .build();

        try {
            githubAPIExecutorService.call(HttpMethod.PATCH,
                    String.format(UPDATE_ISSUE_PATH_TEMPLATE, taskEntity.getGithubId()),
                    request,
                    Map.class
            );
        } catch (ResponseStatusException responseStatEx) {
            logger.warn(
                    LoggingMarkerConstants.TASK,
                    LOG_UPDATE_GITHUB_ISSUE_SERVICE_FAILURE_CALL,
                    taskEntity.getId(),
                    responseStatEx.getReason()
            );
            throw responseStatEx;
        }

        logger.debug(LoggingMarkerConstants.TASK, LOG_UPDATE_GITHUB_ISSUE_SERVICE_SUCCESS_CALL, taskEntity.getId());
    }

}
