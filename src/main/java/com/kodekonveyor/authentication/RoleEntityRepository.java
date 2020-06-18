package com.kodekonveyor.authentication;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface RoleEntityRepository extends CrudRepository<RoleEntity, Long> {

  Optional<RoleEntity> findByName(String string);

}
