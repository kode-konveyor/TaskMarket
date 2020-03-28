package com.kodekonveyor.market.tasks;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.kodekonveyor.market.project.ProjectEntity;
import com.kodekonveyor.market.register.MarketUserEntity;

import lombok.Data;

@Entity
@Data
public class TaskEntity {

  @Id
  private String githubId;
  private String name;
  private ProjectEntity project;
  private MarketUserEntity responsible;
  private TaskStatusEnum status;
}
