package com.kodekonveyor.market.tasks;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodekonveyor.authentication.AuthenticatedUserService;
import com.kodekonveyor.authentication.UserEntity;
import com.kodekonveyor.market.UrlMapConstants;
import com.kodekonveyor.market.kpi.EventEntity;
import com.kodekonveyor.market.kpi.EventEntityRepository;
import com.kodekonveyor.market.kpi.EventTypeEnum;
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
  EventEntityRepository eventEntityRepository;

  @PutMapping(UrlMapConstants.GRAB_TASK_PATH)
  public void call(final long taskId) {
    final TaskEntity taskEntity =
        taskEntityRepository.findById(taskId).get();
    final UserEntity userEntity = authenticatedUserService.call();
    recordGrabDate(taskEntity);
    raiseEvent(userEntity);
  }

  private void
      recordGrabDate(final TaskEntity taskEntity) {
    taskEntity.setGrabDate(new Date());
    taskEntityRepository.save(taskEntity);
  }

  private void
      raiseEvent(final UserEntity userEntity) {
    final EventEntity event = new EventEntity();
    event.setEventType(EventTypeEnum.GRAB);
    event.setDate(DateUtil.getDate());
    event.setUser(userEntity);
    eventEntityRepository.save(event);
  }

}
