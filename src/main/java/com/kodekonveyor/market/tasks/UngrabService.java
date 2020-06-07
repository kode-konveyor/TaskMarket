package com.kodekonveyor.market.tasks;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.jayway.jsonpath.DocumentContext;
import com.kodekonveyor.market.MarketConstants;
import com.kodekonveyor.market.project.PullRequestEntity;
import com.kodekonveyor.market.project.PullrequestEntityRepository;
import com.kodekonveyor.market.technical.GithubConstants;
import com.kodekonveyor.market.technical.GithubGraphqlService;

@Service
public class UngrabService {

  @Autowired
  private TaskEntityRepository taskEntityRepository;

  @Autowired
  private PullrequestEntityRepository pullrequestEntityRepository;

  @Autowired
  private GithubGraphqlService githubGetGraphqlService;

  @Scheduled(fixedRate = GithubConstants.DAILY)
  public void call() {

    checkGrabEligibility();

  }

  private void checkGrabEligibility() {
    final Instant now = Instant.now();

    final List<TaskEntity> taskEntities =
        taskEntityRepository.findByStatus(TaskStatusEnum.IN_PROGRESS);

    for (final TaskEntity taskEntity : taskEntities)
      tasksCheck(now, taskEntity);

    final Iterable<PullRequestEntity> pullRequestList =
        pullrequestEntityRepository.findAll();

    for (final PullRequestEntity pullRequestEntity : pullRequestList)
      pullRequestCheck(now, pullRequestEntity);
  }

  private void
      tasksCheck(final Instant now, final TaskEntity taskEntity) {

    final Duration duration = Duration.between(now, taskEntity.getGrabDate());
    final long durationInDays = Math.abs(duration.toDays());
    final List<PullRequestEntity> pullRequestList =
        pullrequestEntityRepository.findByTask(taskEntity);

    if (
      durationInDays >= MarketConstants.DAYS_UNTIL_UNGRAB &&
          pullRequestList.isEmpty()
    ) {
      taskEntity.setMarketUser(null);
      taskEntity.setStatus(TaskStatusEnum.UP_FOR_GRAB);
      taskEntityRepository.save(taskEntity);
    }

  }

  private void pullRequestCheck(
      final Instant now, final PullRequestEntity pullRequest
  ) {

    final String query = String
        .format(
            GithubConstants.PULL_REQUEST_QUERY, GithubConstants.KODE_KONVEYOR,
            GithubConstants.TASK_MARKET, pullRequest.getId()
        );

    final DocumentContext context = githubGetGraphqlService.call(query);

    final Instant lastCommitDate = Instant
        .parse(context.read(GithubConstants.LAST_COMMIT_DATE_QUERY).toString());
    final String pullRequestStatus =
        context.read(GithubConstants.COMMIT_STATUS_QUERY).toString();
    final Instant lastReviewDate = Instant
        .parse(context.read(GithubConstants.LAST_REVIEW_DATE_QUERY).toString());

    final Duration lastCommitDuration = Duration.between(now, lastCommitDate);
    final long lastCommitDurationInDays = Math.abs(lastCommitDuration.toDays());

    final TaskEntity taskEntity =
        taskEntityRepository.findById(pullRequest.getTask().getId()).get();

    if (
      pullRequestStatus.equals(
          GithubConstants.FAILURE
      ) && lastCommitDurationInDays >= MarketConstants.DAYS_UNTIL_UNGRAB
    ) {
      taskEntity.setMarketUser(null);
      taskEntity.setStatus(TaskStatusEnum.UP_FOR_GRAB);
      taskEntityRepository.save(taskEntity);
    }

    final Duration durationBetweenReviewAndCommit =
        Duration.between(lastReviewDate, lastCommitDate);
    final long durationBetweenReviewAndCommitInDays =
        Math.abs(durationBetweenReviewAndCommit.toDays());

    if (
      pullRequestStatus.equals(
          GithubConstants.SUCCESS
      ) && durationBetweenReviewAndCommitInDays >= MarketConstants.DAYS_UNTIL_UNGRAB
    ) {
      taskEntity.setMarketUser(null);
      taskEntity.setStatus(TaskStatusEnum.UP_FOR_GRAB);
      taskEntityRepository.save(taskEntity);
    }

  }

}
