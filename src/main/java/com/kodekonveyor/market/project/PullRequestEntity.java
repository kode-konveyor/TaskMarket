package com.kodekonveyor.market.project;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.kodekonveyor.market.tasks.TaskEntity;

import lombok.Data;

@Generated("by zenta-tools")
@Data
@Entity
public class PullRequestEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Boolean isAccepted;

  private Long reference;

  @OneToOne(fetch = FetchType.LAZY)
  private TaskEntity task;

}
