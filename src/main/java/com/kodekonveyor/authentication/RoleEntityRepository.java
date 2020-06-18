package com.kodekonveyor.authentication;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface RoleEntityRepository extends CrudRepository<RoleEntity, Long> {

  List<RoleEntity> findByName(String string);

}
