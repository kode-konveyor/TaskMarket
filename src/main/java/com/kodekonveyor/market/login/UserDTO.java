package com.kodekonveyor.market.login;

import com.kodekonveyor.annotations.ExcludeFromCodeCoverage;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@ExcludeFromCodeCoverage("no code")
public class UserDTO {

  private String login;
  private Long id; //NOPMD design verdict
  private String email;

}
