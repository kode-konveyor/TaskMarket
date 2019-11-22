package com.kodekonveyor.authentication;

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
  private Long id;

}
