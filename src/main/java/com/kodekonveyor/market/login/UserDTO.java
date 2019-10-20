package com.kodekonveyor.market.login;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDTO {

  private String login;
  private Long id;
  private String email;
  private String auth0id;
  private String name;
  private String country;
  private String company;
  private String address;
  private String registrationNumber;
  private String representedBy;
  private String skypeName;
  private String paymentChannel;

}
