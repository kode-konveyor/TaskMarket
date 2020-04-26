package com.kodekonveyor.market.project;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.kodekonveyor.authentication.RoleEntity;

public interface ProjectEntityRepository
    extends CrudRepository<ProjectEntity, Long> {

  @Override
  Optional<ProjectEntity> findById(Long projectId);

  Optional<ProjectEntity> findByName(String projectName);

  Optional<ProjectEntity> findByRole(RoleEntity role);

}
