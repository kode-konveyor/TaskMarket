package com.kodekonveyor.market.tasks;

import com.kodekonveyor.market.technical.GithubAPIExecutorService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class UpdateGithubIssueServiceTestBase {

    @InjectMocks
    protected UpdateGithubIssueService updateGithubIssueService;

    @Mock
    protected GithubAPIExecutorService githubAPIExecutorService;
}
