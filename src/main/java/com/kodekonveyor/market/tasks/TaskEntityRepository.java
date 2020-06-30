package com.kodekonveyor.market.tasks;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.kodekonveyor.market.register.MarketUserEntity;

public interface TaskEntityRepository extends CrudRepository<TaskEntity, Long> {

  List<TaskEntity> findByStatus(TaskStatusEnum status);

  List<TaskEntity> findByStatusAndMarketUser(
      TaskStatusEnum inProgress, MarketUserEntity marketUser
  );

  Optional<TaskEntity>
      findByServiceAndBehaviour(String service, String behaviour);

  List<TaskEntity> findByMarketUser(MarketUserEntity marketUserEntity);

}
