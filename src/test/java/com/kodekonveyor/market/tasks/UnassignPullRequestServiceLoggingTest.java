package com.kodekonveyor.market.tasks;

import com.kodekonveyor.annotations.TestedBehaviour;
import com.kodekonveyor.annotations.TestedService;
import com.kodekonveyor.exception.ThrowableTester;
import com.kodekonveyor.market.project.PullRequestDTOTestData;
import com.kodekonveyor.market.project.PullRequestEntity;
import com.kodekonveyor.market.project.PullRequestEntityStubs;
import com.kodekonveyor.market.project.PullRequestTestData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static com.kodekonveyor.market.tasks.GetRepositoryTasksServiceTestData.TASK;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("Logging")
@TestedService("UnassignPullRequestService")
public class UnassignPullRequestServiceLoggingTest extends UnassignPullRequestServiceTestBase {

    @Captor
    private ArgumentCaptor<PullRequestEntity> captor;


    @Test
    @DisplayName("Start of service execution for UnassignPullRequestService is logged.")
    public void test1() {
        PullRequestEntityStubs.behaviourSaveAndCaptureArgs(pullrequestEntityRepository, captor);
        unassignPullRequestService.call(PullRequestDTOTestData.get());

        Mockito.verify(logger)
                .info(
                        TASK,
                        TaskTestData.EXP_LOG_ASSIGN_PR_CALL,
                        TaskTestData.ID
                );
    }

    @Test
    @DisplayName("Successful completion of service execution for UnassignPullRequestService is logged.")
    public void test2() {
        PullRequestEntityStubs.behaviourSaveAndCaptureArgs(pullrequestEntityRepository, captor);
        unassignPullRequestService.call(PullRequestDTOTestData.get());

        Mockito.verify(logger)
                .debug(
                        TASK,
                        TaskTestData.EXP_LOG_ASSIGN_PR_CALL_SUCCESS,
                        TaskTestData.ID
                );
    }

    @Test
    @DisplayName("Error for service execution for UnassignPullRequestService is logged, when PR is not found.")
    public void test3() {
        ThrowableTester.assertThrows(() -> unassignPullRequestService.call(PullRequestDTOTestData.getIdNotExists()));

        Mockito.verify(logger)
                .error(
                        TASK,
                        TaskTestData.EXP_LOG_ASSIGN_PR_CALL_FAILURE,
                        PullRequestTestData.EXP_PR_NOT_FOUND
                );
    }
}
