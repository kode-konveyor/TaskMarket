package com.kodekonveyor.market.tasks;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TaskEntityRepository extends CrudRepository<TaskEntity, Long> {


  List<TaskEntity> findByStatus(TaskStatusEnum status);


}
