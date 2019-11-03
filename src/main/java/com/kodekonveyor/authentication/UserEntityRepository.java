package com.kodekonveyor.authentication;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface UserEntityRepository extends CrudRepository<UserEntity,Long>{

	List<UserEntity> findByAuth0id(String auth0id);
}
