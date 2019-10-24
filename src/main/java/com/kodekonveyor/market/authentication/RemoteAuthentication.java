package com.kodekonveyor.market.authentication;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.kodekonveyor.market.login.User;

public class RemoteAuthentication implements Authentication {

  private static final long serialVersionUID = 1L;
  private final User user;

  public RemoteAuthentication(final User user) {
    this.user = user;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return new ArrayList<>(0);
  }

  @Override
  public Object getCredentials() {
    return user.getAuth0id();
  }

  @Override
  public Object getDetails() {
    return user;
  }

  @Override
  public Object getPrincipal() {
    return user.getLogin();
  }

  @Override
  public boolean isAuthenticated() {
    return true;
  }

  @Override
  public void setAuthenticated(final boolean isAuthenticated)
      throws IllegalArgumentException {
  }

  @Override
  public String getName() {
    return user.getLogin();
  }
}
