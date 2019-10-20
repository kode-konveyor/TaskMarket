package com.kodekonveyor.market.login;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
@Table(name = "market_users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(name = "username")
  private String login;
  @Column(name = "auth0id")
  private String auth0id;
  @Column(name = "email")
  private String email;
  private String name;
  private String country;
  private String company;
  private String address;
  private String registrationNumber;
  private String representedBy;
  private String skypeName;
  private String paymentChannel;
}
