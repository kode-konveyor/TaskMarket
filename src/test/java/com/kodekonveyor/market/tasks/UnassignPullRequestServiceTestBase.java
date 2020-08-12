package com.kodekonveyor.market.tasks;

import com.kodekonveyor.market.project.PullRequestEntityStubs;
import com.kodekonveyor.market.project.PullrequestEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class UnassignPullRequestServiceTestBase {

    @InjectMocks
    protected UnassignPullRequestService unassignPullRequestService;

    @Mock
    protected PullrequestEntityRepository pullrequestEntityRepository;

    @BeforeEach
    void setUp() {
        PullRequestEntityStubs.behaviour(pullrequestEntityRepository);
    }
}
