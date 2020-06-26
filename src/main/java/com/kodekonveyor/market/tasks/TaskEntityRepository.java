package com.kodekonveyor.market.tasks;

import java.util.List;

import com.kodekonveyor.market.register.MarketUserEntity;
import org.springframework.data.repository.CrudRepository;

public interface TaskEntityRepository extends CrudRepository<TaskEntity, Long> {


  List<TaskEntity> findByStatus(TaskStatusEnum status);

  List<TaskEntity> findByMarketUser(MarketUserEntity marketUserEntity);

}
