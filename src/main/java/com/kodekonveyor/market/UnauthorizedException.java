package com.kodekonveyor.market;

import com.kodekonveyor.annotations.InterfaceClass;

@InterfaceClass
public class UnauthorizedException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public UnauthorizedException(final String message) {
    super(message);
  }
}
