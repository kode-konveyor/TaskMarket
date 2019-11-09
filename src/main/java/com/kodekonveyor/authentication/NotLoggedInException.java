package com.kodekonveyor.authentication;

import com.kodekonveyor.annotations.InterfaceClass;

import lombok.Getter;

@Getter
@InterfaceClass
public class NotLoggedInException extends RuntimeException {

  private static final long serialVersionUID = -4900004519786666447L;

  public NotLoggedInException(final String message) {
    super(message);
  }
}
