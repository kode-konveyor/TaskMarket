package com.kodekonveyor.market.project;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.kodekonveyor.market.tasks.TaskEntity;

public interface PullrequestEntityRepository
    extends CrudRepository<PullRequestEntity, Long> {

  List<PullRequestEntity> findByTask(TaskEntity taskEntity);

}
