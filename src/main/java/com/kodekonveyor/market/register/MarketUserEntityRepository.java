package com.kodekonveyor.market.register;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.kodekonveyor.authentication.UserEntity;

public interface MarketUserEntityRepository
    extends CrudRepository<MarketUserEntity, Long> {

  Optional<MarketUserEntity> findByUser(UserEntity userEntity);

  List<MarketUserEntity> findAllByUser(List<UserEntity> users);

}
