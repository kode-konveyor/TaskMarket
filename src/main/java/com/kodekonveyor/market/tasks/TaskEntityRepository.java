package com.kodekonveyor.market.tasks;

import java.util.Optional;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.kodekonveyor.market.register.MarketUserEntity;

public interface TaskEntityRepository extends CrudRepository<TaskEntity, Long> {

    Optional<TaskEntity> findByServiceAndBehaviour(String service, String behaviour);

    List<TaskEntity> findByStatusAndProjectIsPublic(TaskStatusEnum status, boolean isPublic);

    List<TaskEntity> findByStatusAndResponsible(TaskStatusEnum status, MarketUserEntity marketUserEntity);

    List<TaskEntity> findByStatusAndResponsibleAndProjectIsPublic(TaskStatusEnum status,
            MarketUserEntity marketUserEntity, boolean isPublic);
}
