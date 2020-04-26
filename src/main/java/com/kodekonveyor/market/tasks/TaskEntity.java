package com.kodekonveyor.market.tasks;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.kodekonveyor.market.project.MilestoneEntity;

import lombok.Data;

@Generated("by zenta-tools")
@Data
@Entity
public class TaskEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private MilestoneEntity milestone;
  private String behaviour;
  private String description;
  private Long githubId;
  private String service;

}
