package com.kodekonveyor.authentication;

public class ValidationException extends RuntimeException {

  /**
   *
   */
  private static final long serialVersionUID = -3791127407824906027L; //NOPMD

  public ValidationException(final String nullOwnerId) {
    super(nullOwnerId);
  }
}
