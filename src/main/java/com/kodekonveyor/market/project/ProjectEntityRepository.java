package com.kodekonveyor.market.project;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface ProjectEntityRepository
    extends CrudRepository<ProjectEntity, Long> {

  @Override
  Optional<ProjectEntity> findById(Long projectId);

  List<ProjectEntity> findByName(String name);
}
