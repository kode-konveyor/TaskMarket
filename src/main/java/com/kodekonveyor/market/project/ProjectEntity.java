package com.kodekonveyor.market.project;

import java.util.Set;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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

  private String description;

  private String projectId;

  private String url;

  @OneToMany(fetch = FetchType.LAZY)
  private Set<PullRequestEntity> pullRequest;

  @OneToMany(fetch = FetchType.LAZY)
  private Set<RoleEntity> role;

  @OneToMany(fetch = FetchType.LAZY)
  private Set<MilestoneEntity> milestone;

}
