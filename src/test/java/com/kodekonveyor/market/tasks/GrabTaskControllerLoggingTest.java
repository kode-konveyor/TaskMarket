package com.kodekonveyor.market.tasks;

import com.kodekonveyor.annotations.TestedBehaviour;
import com.kodekonveyor.annotations.TestedService;
import com.kodekonveyor.authentication.AuthenticatedUserServiceStubs;
import com.kodekonveyor.exception.ThrowableTester;
import com.kodekonveyor.market.project.PullRequestEntityStubs;
import com.kodekonveyor.market.register.MarketUserEntityRepositoryStubs;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static com.kodekonveyor.logging.LoggingMarkerConstants.TASK;
import static com.kodekonveyor.market.tasks.TaskTestData.EXP_LOG_GRAB_TASK_FAILURE_CALL;
import static com.kodekonveyor.market.tasks.TaskTestData.EXP_USER_NOT_ELIGIBLE_TO_GRAB;
import static com.kodekonveyor.market.tasks.TaskTestData.TASK_NOT_UP_FOR_GRAB;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("Logging")
@TestedService("GrabTaskController")
public class GrabTaskControllerLoggingTest
        extends GrabTaskControllerTestBase {

    @Test
    @DisplayName(
            "Start of the api execution, for grabbing the task, is logged."
    )
    public void test1() {
        AuthenticatedUserServiceStubs.unregistered(authenticatedUserService);
        MarketUserEntityRepositoryStubs.userNewlyRegistered(marketUserEntityRepository);
        grabTaskController.call(TaskTestData.ID);

        Mockito.verify(logger)
                .info(
                        TASK,
                        TaskTestData.EXP_LOG_GRAB_TASK_CALL,
                        TaskTestData.ID
                );
    }

    @Test
    @DisplayName(
            "End of the api execution, for grabbing the task, is logged."
    )
    public void test2() {
        AuthenticatedUserServiceStubs.unregistered(authenticatedUserService);
        MarketUserEntityRepositoryStubs.userNewlyRegistered(marketUserEntityRepository);
        grabTaskController.call(TaskTestData.ID);

        Mockito.verify(logger)
                .debug(
                        TASK,
                        TaskTestData.EXP_LOG_GRAB_TASK_SUCCESS_CALL,
                        TaskTestData.ID
                );
    }

    @Test
    @DisplayName(
            "Error is logged, when user is not eligible to grab the task."
    )
    public void test3() {
        PullRequestEntityStubs.pullRequestNotIssued(pullrequestEntityRepository);
        ThrowableTester.assertThrows(() -> grabTaskController.call(TaskTestData.ID));
        Mockito.verify(logger)
                .warn(
                        TASK,
                        EXP_LOG_GRAB_TASK_FAILURE_CALL,
                        EXP_USER_NOT_ELIGIBLE_TO_GRAB
                );
    }

    @Test
    @DisplayName(
            "Error is logged, when user is not eligible to grab the task."
    )
    public void test4() {
        ThrowableTester.assertThrows(
                () -> grabTaskController.call(TaskTestData.ID_IN_PROGRESS)
        ).assertMessageIs(TASK_NOT_UP_FOR_GRAB);

        Mockito.verify(logger)
                .warn(
                        TASK,
                        EXP_LOG_GRAB_TASK_FAILURE_CALL,
                        TASK_NOT_UP_FOR_GRAB
                );
    }
}
