package com.kodekonveyor.market.tasks;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodekonveyor.authentication.AuthenticatedUserService;
import com.kodekonveyor.authentication.UserEntity;
import com.kodekonveyor.market.UrlMapConstants;
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
  UngrabService ungrabService;

  @PutMapping(UrlMapConstants.GRAB_TASK_PATH)
  public void call(final long taskId) {
    final TaskEntity taskEntity =
        taskEntityRepository.findById(taskId).get();
    final UserEntity userEntity = authenticatedUserService.call();
    final MarketUserEntity marketUserEntity =
        marketUserEntityRepository.findByUser(userEntity).get();
    recordGrabDate(taskEntity);
  }

  private void recordGrabDate(final TaskEntity taskEntity) {
    taskEntity.setGrabDate(LocalDateTime.now());
  }

}
