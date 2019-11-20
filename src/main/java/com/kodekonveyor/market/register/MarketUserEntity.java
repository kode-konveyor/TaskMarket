package com.kodekonveyor.market.register;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.kodekonveyor.authentication.UserEntity;

import lombok.Data;

@Data
@Entity
class MarketUserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private UserLegalInfoEntity legal;

  private UserEntity login;

}
