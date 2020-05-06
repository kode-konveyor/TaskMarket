package com.kodekonveyor.market.project;

import java.util.Set;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.kodekonveyor.market.tasks.TaskEntity;

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

  @OneToMany(fetch = FetchType.LAZY)
  private Set<TaskEntity> task;

}
