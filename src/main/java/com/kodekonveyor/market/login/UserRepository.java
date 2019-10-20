package com.kodekonveyor.market.login;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long>{

	List<User> findByAuth0id(String auth0id);
}
