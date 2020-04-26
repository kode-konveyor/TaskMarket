package com.kodekonveyor.market.project;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Generated("by zenta-tools")
@Data
@Entity
public class MilestoneEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Boolean isActive;
  private String name;
  private Long priority;

}
