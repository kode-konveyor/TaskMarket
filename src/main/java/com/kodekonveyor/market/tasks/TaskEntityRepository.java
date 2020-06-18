package com.kodekonveyor.market.tasks;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.kodekonveyor.market.register.MarketUserEntity;

public interface TaskEntityRepository extends CrudRepository<TaskEntity, Long> {

  List<TaskEntity> findByStatus(TaskStatusEnum status);

  List<TaskEntity> findByStatusAndMarketUser(
      TaskStatusEnum inProgress, MarketUserEntity marketUser
  );

}
