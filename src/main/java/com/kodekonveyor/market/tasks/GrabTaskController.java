package com.kodekonveyor.market.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodekonveyor.authentication.AuthenticatedUserService;
import com.kodekonveyor.authentication.UserEntity;
import com.kodekonveyor.market.UrlMapConstants;
import com.kodekonveyor.market.ValidationException;
import com.kodekonveyor.market.register.MarketUserEntity;
import com.kodekonveyor.market.register.MarketUserEntityRepository;

@RestController
public class GrabTaskController {

  @Autowired
  TaskEntityRepository taskEntityRepository;

  @Autowired
  AuthenticatedUserService authenticatedUserService;

  @Autowired
  MarketUserEntityRepository marketUserEntityRepository;

  @Autowired
  UpdateGithubIssueService updateGithubIssueService;

  @PutMapping(UrlMapConstants.GRAB_TASK_PATH)
  public void call(final long taskId) {
    final TaskEntity taskEntity =
        taskEntityRepository.findById(taskId).get();
    final UserEntity userEntity = authenticatedUserService.call();
    final MarketUserEntity marketUserEntity =
        marketUserEntityRepository.findByUser(userEntity).get();

    validateTask(taskEntity);
    updateTask(taskEntity, marketUserEntity);

  }

  private void validateTask(final TaskEntity taskEntity) {
    if (!taskEntity.getStatus().equals(TaskStatusEnum.UP_FOR_GRAB))
      throw new ValidationException(TaskConstants.TASK_NOT_UP_FOR_GRAB);
  }

  private void updateTask(
      final TaskEntity taskEntity, final MarketUserEntity marketUserEntity
  ) {
    taskEntity.setStatus(TaskStatusEnum.IN_PROGRESS);
    taskEntity.setMarketUser(marketUserEntity);
    taskEntityRepository.save(taskEntity);
    updateGithubIssueService.call(taskEntity);
  }

}
