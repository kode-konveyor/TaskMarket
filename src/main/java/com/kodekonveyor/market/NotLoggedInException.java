package com.kodekonveyor.market;

import lombok.Getter;

@Getter
public class NotLoggedInException extends RuntimeException {

  private static final long serialVersionUID = -4900004519786666447L;

  String redirectUri;

  public NotLoggedInException(final String message, final String uri) {
    super(message);
    redirectUri = uri;
  }
}
