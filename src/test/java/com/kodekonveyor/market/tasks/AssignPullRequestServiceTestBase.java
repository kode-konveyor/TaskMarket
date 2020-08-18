package com.kodekonveyor.market.tasks;

import com.kodekonveyor.market.DTOToEntityConverterService;
import com.kodekonveyor.market.DTOToEntityConverterServiceStubs;
import com.kodekonveyor.market.PullRequestEntityToDTOConverterService;
import com.kodekonveyor.market.EntityToDTOConverterServiceStubs;
import com.kodekonveyor.market.project.PullRequestEntityStubs;
import com.kodekonveyor.market.project.PullrequestEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class AssignPullRequestServiceTestBase {

    @InjectMocks
    protected AssignPullRequestService assignPullRequestService;

    @Mock
    protected PullrequestEntityRepository pullrequestEntityRepository;

    @Mock
    protected TaskEntityRepository taskEntityRepository;

    @Mock
    protected UnassignPullRequestService unassignPullRequestService;

    @Mock
    protected DTOToEntityConverterService dtoToEntityConverterService;

    @Mock
    protected PullRequestEntityToDTOConverterService pullRequestEntityToDTOConverterService;

    @BeforeEach
    void setUp() {
        TaskEntityRepositoryStubs.behaviour(taskEntityRepository);
        PullRequestEntityStubs.behaviour(pullrequestEntityRepository);
        UnassignPullRequestServiceStubs.behaviour(unassignPullRequestService);
        DTOToEntityConverterServiceStubs.behaviour(dtoToEntityConverterService);
        EntityToDTOConverterServiceStubs.behaviour(pullRequestEntityToDTOConverterService);
    }
}
