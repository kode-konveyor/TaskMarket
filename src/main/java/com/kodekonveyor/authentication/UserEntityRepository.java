package com.kodekonveyor.authentication;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface UserEntityRepository extends CrudRepository<UserEntity, Long> {

  List<UserEntity> findByLogin(String login);
}
