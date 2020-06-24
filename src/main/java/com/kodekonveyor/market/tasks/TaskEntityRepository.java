package com.kodekonveyor.market.tasks;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface TaskEntityRepository extends CrudRepository<TaskEntity, Long> {

  List<TaskEntity> findByStatus(TaskStatusEnum status);

  Optional<TaskEntity>
      findByServiceAndBehaviour(String service, String behaviour);

}
