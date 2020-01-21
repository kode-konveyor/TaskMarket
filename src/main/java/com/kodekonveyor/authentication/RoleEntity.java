package com.kodekonveyor.authentication;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode
@Table(name = "roles")
public class RoleEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "rolename")
  private String name;
  @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
  @EqualsAndHashCode.Exclude
  private Set<UserEntity> users = new HashSet<>();
}
