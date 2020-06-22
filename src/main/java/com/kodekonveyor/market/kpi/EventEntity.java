package com.kodekonveyor.market.kpi;

import java.util.Date;

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
public class EventEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private EventTypeEnum eventType;

  private Date date;

  @OneToOne(fetch = FetchType.LAZY)
  private UserEntity user;

}
