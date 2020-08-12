package com.kodekonveyor.market.tasks;

import com.kodekonveyor.annotations.TestedBehaviour;
import com.kodekonveyor.annotations.TestedService;
import com.kodekonveyor.exception.ThrowableTester;
import com.kodekonveyor.market.ValidationException;
import com.kodekonveyor.market.project.PullRequestDTOTestData;
import com.kodekonveyor.market.project.PullRequestEntity;
import com.kodekonveyor.market.project.PullRequestEntityStubs;
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

import static com.kodekonveyor.market.tasks.TaskTestData.EXP_TASK_NOT_FOUND;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("associate pull request to task")
@TestedService("AssignPullRequestService")
public class AssignPullRequestServiceAssociatePullRequestToTaskTest extends AssignPullRequestServiceTestBase {

    @Captor
    private ArgumentCaptor<PullRequestEntity> captor;

    @Test
    @DisplayName("Task entity is associated to PR and returned successfully.")
    public void test1() {
        PullRequestEntityStubs.behaviourSaveAndCaptureArgs(pullrequestEntityRepository, captor);
        TaskEntity actualOutput = assignPullRequestService.call(PullRequestDTOTestData.get());

        Assert.assertEquals(TaskEntityTestData.get(), actualOutput);
    }

    @Test
    @DisplayName("When task is not found, ValidationException is thrown")
    public void test2() {
        ThrowableTester.assertThrows(() -> assignPullRequestService.call(PullRequestDTOTestData.getTaskNotFound()))
                .assertException(ValidationException.class);

    }

    @Test
    @DisplayName("When task is not found, error message is : Task not found")
    public void test3() {
        ThrowableTester.assertThrows(() -> assignPullRequestService.call(PullRequestDTOTestData.getTaskNotFound()))
                .assertMessageIs(EXP_TASK_NOT_FOUND);

    }

    @Test
    @DisplayName("When task is already associated with PRs, then its unassigned first from other PRs.")
    public void test4() {
        PullRequestEntityStubs.behaviourSaveAndCaptureArgs(pullrequestEntityRepository, captor);
        PullRequestEntityStubs.pullRequestIssued(pullrequestEntityRepository);

        assignPullRequestService.call(PullRequestDTOTestData.get());

        Mockito.verify(unassignPullRequestService).call(any());
    }

    @Test
    @DisplayName("When task is not associated with other PRs, then UnassignPullRequestService is not invoked.")
    public void test5() {
        PullRequestEntityStubs.behaviourSaveAndCaptureArgs(pullrequestEntityRepository, captor);
        assignPullRequestService.call(PullRequestDTOTestData.get());
        Mockito.verify(unassignPullRequestService, Mockito.never()).call(any());
    }

    @Test
    @DisplayName("TaskEntity saved with PR, has the Id sama as the one in input.")
    public void test6() {
        PullRequestEntityStubs.behaviourSaveAndCaptureArgs(pullrequestEntityRepository, captor);

        assignPullRequestService.call(PullRequestDTOTestData.get());

        Assert.assertEquals(TaskEntityTestData.get(), captor.getValue().getTask());
    }

    @Test
    @DisplayName("Task is associated and saved with pull request in db.")
    public void test7() {
        PullRequestEntityStubs.behaviourSaveAndCaptureArgs(pullrequestEntityRepository, captor);

        assignPullRequestService.call(PullRequestDTOTestData.get());

        Mockito.verify(pullrequestEntityRepository).save(any());
    }

    @Test
    @DisplayName("Task entity returned is same as the one associated with PR.")
    public void test8() {
        PullRequestEntityStubs.behaviourSaveAndCaptureArgs(pullrequestEntityRepository, captor);
        TaskEntity actualOutput = assignPullRequestService.call(PullRequestDTOTestData.get());
        Assert.assertTrue(captor.getValue().getTask() == actualOutput);
    }
}
