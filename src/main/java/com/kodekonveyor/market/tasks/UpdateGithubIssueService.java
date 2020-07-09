package com.kodekonveyor.market.tasks;

import com.google.common.collect.ImmutableMap;
import com.kodekonveyor.market.technical.GithubAPIExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

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

    public void call(final TaskEntity taskEntity) {
        Map<String, Object> request = buildGithubRequest(taskEntity);

        githubAPIExecutorService.call(HttpMethod.PATCH,
                String.format(UPDATE_ISSUE_PATH_TEMPLATE, taskEntity.getGithubId()),
                request,
                Map.class
        );

    }

    private Map<String, Object> buildGithubRequest(final TaskEntity taskEntity) {
        return ImmutableMap.<String, Object>builder()
                .put(BODY, taskEntity.getDescription())
                .put(TITLE, String.format(TITLE_TEMPLATE, taskEntity.getService(), taskEntity.getBehaviour()))
                .put(LABELS, Optional.ofNullable(taskEntity.getStatus())
                        .map(val -> Set.of(val.getValue()))
                        .orElse(Collections.emptySet())
                )
                .put(ASSIGNEES, Optional.ofNullable(taskEntity.getMarketUser())
                        .map(val -> Set.of(val.getUser().getLogin()))
                        .orElse(Collections.emptySet())
                )
                .build();
    }

}
