package com.kodekonveyor.market.tasks;

import com.kodekonveyor.annotations.TestedBehaviour;
import com.kodekonveyor.annotations.TestedService;
import com.kodekonveyor.exception.ThrowableTester;
import com.kodekonveyor.market.ValidationException;
import com.kodekonveyor.market.project.PullRequestDTOTestData;
import com.kodekonveyor.market.project.PullRequestEntity;
import com.kodekonveyor.market.project.PullRequestEntityStubs;
import com.kodekonveyor.market.project.PullRequestTestData;
import org.junit.Assert;
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

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("unassign pull request")
@TestedService("UnassignPullRequestService")
public class UnassignPullRequestServiceUnassignPullRequestTest extends UnassignPullRequestServiceTestBase {

    @Captor
    private ArgumentCaptor<PullRequestEntity> captor;

    @Test
    @DisplayName("Pull request is disassociated by setting task as null.")
    public void test1() {
        PullRequestEntityStubs.behaviourSaveAndCaptureArgs(pullrequestEntityRepository, captor);
        unassignPullRequestService.call(PullRequestDTOTestData.get());
        Assert.assertNull(captor.getValue().getTask());
    }

    @Test
    @DisplayName("Pull request is saved to db, after disassociating task.")
    public void test2() {
        unassignPullRequestService.call(PullRequestDTOTestData.get());
        Mockito.verify(pullrequestEntityRepository).save(any(PullRequestEntity.class));
    }

    @Test
    @DisplayName("If pull request is not found, ValidationException is thrown.")
    public void test3() {
        ThrowableTester.assertThrows(() -> unassignPullRequestService.call(PullRequestDTOTestData.getTaskNotFound()))
                .assertException(ValidationException.class);
    }

    @Test
    @DisplayName("If pull request is not found, message is : Pull Request not found")
    public void test4() {
        ThrowableTester.assertThrows(() -> unassignPullRequestService.call(PullRequestDTOTestData.getTaskNotFound()))
                .assertMessageIs(PullRequestTestData.EXP_PR_NOT_FOUND);
    }

    @Test
    @DisplayName("Task entity associated with PR is returned successfully, after pull request is unassigned.")
    public void test5() {
        PullRequestEntityStubs.behaviourSaveAndCaptureArgs(pullrequestEntityRepository, captor);
        TaskEntity actualOutput = unassignPullRequestService.call(PullRequestDTOTestData.get());
        Assert.assertEquals(TaskEntityTestData.get(), actualOutput);
    }
}
