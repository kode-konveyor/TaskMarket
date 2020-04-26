package com.kodekonveyor.market.project;

import java.util.Set;

import javax.annotation.Generated;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.kodekonveyor.market.tasks.TaskEntity;

import lombok.Data;

@Generated("by zenta-tools")
@Data
@Entity
public class ProjectModelEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ElementCollection
  private Set<MilestoneEntity> milestone;
  @ElementCollection
  private Set<TaskEntity> task;

}
