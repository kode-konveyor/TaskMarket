package com.kodekonveyor.market.project;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.kodekonveyor.market.tasks.TaskEntity;

public interface MilestoneEntityRepository
    extends CrudRepository<MilestoneEntity, Long> {

  Optional<MilestoneEntity> findByTask(TaskEntity entity);

}
