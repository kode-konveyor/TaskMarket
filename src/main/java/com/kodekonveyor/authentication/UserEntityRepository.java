package com.kodekonveyor.authentication;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface UserEntityRepository extends CrudRepository<UserEntity, Long> {

  Optional<UserEntity> findByLogin(String login);
}
