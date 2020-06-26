package com.kodekonveyor.market.tasks;

import com.kodekonveyor.annotations.TestedBehaviour;
import com.kodekonveyor.annotations.TestedService;
import com.kodekonveyor.authentication.AuthenticatedUserServiceStubs;
import com.kodekonveyor.exception.ThrowableTester;
import com.kodekonveyor.market.ValidationException;
import com.kodekonveyor.market.project.PullRequestEntityStubs;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static com.kodekonveyor.market.register.MarketUserEntityRepositoryStubs.userNewlyRegistered;
import static com.kodekonveyor.market.tasks.TaskTestData.EXP_USER_NOT_ELIGIBLE_TO_GRAB;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("grab eligibility")
@TestedService("GrabTaskController")
public class GrabTaskControllerGrabEligibilityTest
        extends GrabTaskControllerTestBase {

    @Test
    @DisplayName(
            "No exception is thrown and user is eligible to grab the task, if no tasks are found for user."
    )
    public void test1() {
        AuthenticatedUserServiceStubs.unregistered(authenticatedUserService);
        userNewlyRegistered(marketUserEntityRepository);
        ThrowableTester.assertNoException(() -> grabTaskController.call(TaskTestData.ID));
    }

    @Test
    @DisplayName(
            "No exception is thrown and user is eligible to grab the task, if all tasks have pr associated with it."
    )
    public void test2() {
        ThrowableTester.assertNoException(() -> grabTaskController.call(TaskTestData.ID));
    }

    @Test
    @DisplayName(
            "If any task does not have pr associated with it, exception is thrown."
    )
    public void test3() {
        PullRequestEntityStubs.pullRequestNotIssued(pullrequestEntityRepository);
        ThrowableTester.assertThrows(() -> grabTaskController.call(TaskTestData.ID))
                .assertException(ValidationException.class);
    }

    @Test
    @DisplayName(
            "If any of the tasks does not have pr associated with it, error message is : User not eligible to grab task."
    )
    public void test4() {
        PullRequestEntityStubs.pullRequestNotIssued(pullrequestEntityRepository);
        ThrowableTester.assertThrows(() -> grabTaskController.call(TaskTestData.ID))
                .assertMessageIs(EXP_USER_NOT_ELIGIBLE_TO_GRAB);
    }

}
