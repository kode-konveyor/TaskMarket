package com.kodekonveyor.market.project;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.kodekonveyor.market.tasks.TaskEntity;

import lombok.Data;

@Generated("by zenta-tools")
@Data
@Entity
public class PullrequestEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Boolean isAccepted;
  private Long reference;
  private TaskEntity task;

}
