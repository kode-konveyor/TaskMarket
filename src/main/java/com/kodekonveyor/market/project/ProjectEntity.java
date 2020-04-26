package com.kodekonveyor.market.project;

import java.util.Set;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.kodekonveyor.authentication.RoleEntity;

import lombok.Data;

@Generated("by zenta-tools")
@Data
@Entity
public class ProjectEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long budgetInCents;
  private Boolean isPublic;
  private String name;
  private Set<PullrequestEntity> pullRequest;
  private Set<RoleEntity> role;
  private Set<MilestoneEntity> milestone;

}
