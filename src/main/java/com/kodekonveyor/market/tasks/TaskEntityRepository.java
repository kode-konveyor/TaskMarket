package com.kodekonveyor.market.tasks;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.kodekonveyor.market.register.MarketUserEntity;

public interface TaskEntityRepository extends CrudRepository<TaskEntity, Long> {

  Optional<TaskEntity>
      findByServiceAndBehaviour(String service, String behaviour);

  List<TaskEntity>
      findByStatusAndProjectIsPublic(TaskStatusEnum status, boolean isPublic);

  List<TaskEntity> findByStatusAndMarketUser(
      TaskStatusEnum status, MarketUserEntity marketUserEntity
  );

  List<TaskEntity> findByStatusAndMarketUserAndProjectIsPublic(
      TaskStatusEnum status,
      MarketUserEntity marketUserEntity, boolean isPublic
  );
}
