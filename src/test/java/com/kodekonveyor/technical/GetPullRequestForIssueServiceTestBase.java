package com.kodekonveyor.technical;

import com.kodekonveyor.market.technical.GetPullRequestForIssueService;
import com.kodekonveyor.market.technical.GithubGraphqlService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;

public class GetPullRequestForIssueServiceTestBase {

    @InjectMocks
    GetPullRequestForIssueService getPullRequestForIssueService;

    @Mock
    GithubGraphqlService githubGraphqlService;

    @Mock
    Logger loggerService;

}
