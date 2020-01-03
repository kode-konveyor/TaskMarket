package com.kodekonveyor.market;

public class ValidationException extends RuntimeException {

  private static final long serialVersionUID = 3353849516101876332L;

  public ValidationException(final String exception) {
    super(exception);
  }

}
