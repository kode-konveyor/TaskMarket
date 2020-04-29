package com.kodekonveyor.market.tasks;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.kodekonveyor.market.project.MilestoneEntity;
import com.kodekonveyor.market.project.ProjectEntity;
import com.kodekonveyor.market.register.MarketUserEntity;

import lombok.Data;

@Generated("by zenta-tools")
@Data
@Entity
public class TaskEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @OneToOne
  private MilestoneEntity milestone;
  private String behaviour;
  private String description;
  private Long githubId;
  private String service;
  @OneToOne(fetch = FetchType.LAZY)
  private ProjectEntity project;
  @OneToOne(fetch = FetchType.LAZY)
  private MarketUserEntity responsible;
  private TaskStatusEnum status;
}
