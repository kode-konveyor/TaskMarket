package com.kodekonveyor.authentication;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.kodekonveyor.annotations.ExcludeFromCodeCoverage;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
@ExcludeFromCodeCoverage("no code")
@Table(name = "users")
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;// NOPMD
  @Column(name = "username")
  private String login;
  @Column(name = "auth0id")
  private String auth0id;

}
