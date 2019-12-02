package com.kodekonveyor.market.register;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.kodekonveyor.authentication.UserEntity;

import lombok.Data;

@Data
@Entity
public
class MarketUserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @OneToOne(fetch = FetchType.LAZY)
  private UserLegalInfoEntity legal;

  @OneToOne(fetch = FetchType.LAZY)
  private UserEntity login;

}
