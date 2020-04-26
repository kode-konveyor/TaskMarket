package com.kodekonveyor.authentication;

import java.util.Set;

import javax.annotation.Generated;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

import lombok.Data;

@Generated("by zenta-tools")
@Data
@Entity
@Component
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String login;
  @ElementCollection
  private Set<RoleEntity> role;

}
