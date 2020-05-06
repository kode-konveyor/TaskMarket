package com.kodekonveyor.authentication;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.kodekonveyor.annotations.TestedBehaviour;
import com.kodekonveyor.annotations.TestedService;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("authentication infrastructure")
@TestedService("RemoteAuthentication")
public class RemoteAuthenticationAuthenticationInfrastructureTest
    extends RemoteAuthenticationTestBase {

  @DisplayName("getAuthorities returns an empty list")
  @Test
  public void test() {
    assertEquals(
        List.of(), remoteAuthentication.getAuthorities()
    );
  }

  @DisplayName("getCredentials returns the login name")
  @Test
  public void test1() {
    assertEquals(
        UserTestData.LOGIN, remoteAuthentication.getCredentials()
    );
  }

  @DisplayName("getDetails returns the user")
  @Test
  public void test2() {
    assertEquals(UserEntityTestData.get(), remoteAuthentication.getDetails());
  }

  @DisplayName("the returned user has the correct id")
  @Test
  public void test21() {
    assertEquals(
        UserTestData.ID,
        ((UserEntity) remoteAuthentication.getDetails()).getId()
    );
  }

  @DisplayName("getPrincipal returns the login name")
  @Test
  public void test3() {
    assertEquals(UserTestData.LOGIN, remoteAuthentication.getPrincipal());
  }

  @DisplayName("getName returns the login name")
  @Test
  public void test31() {
    assertEquals(UserTestData.LOGIN, remoteAuthentication.getName());
  }

  @DisplayName("isAuthenticated returns true")
  @Test
  public void test4() {
    assertThat(remoteAuthentication.isAuthenticated()).isTrue();
  }

  @DisplayName("setAuthenticated throws IllegalArgumentException")
  @Test
  public void test5() {
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> remoteAuthentication.setAuthenticated(true)
    );
  }

}
