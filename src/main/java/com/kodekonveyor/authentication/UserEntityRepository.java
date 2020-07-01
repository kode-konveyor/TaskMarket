package com.kodekonveyor.authentication;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface UserEntityRepository extends CrudRepository<UserEntity, Long> {

  Optional<UserEntity> findByLogin(String login);

  List<UserEntity> findByRole(RoleEntity roleEntity);
}
