package com.kodekonveyor.market.lead;

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
import com.kodekonveyor.authentication.AuthenticatedUserServiceStubs;
import com.kodekonveyor.exception.ThrowableTester;
import com.kodekonveyor.market.UnauthorizedException;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("roles")
@TestedService("ListLeadController")
public class ListleadControllerRolesTest extends ListLeadControllerTestBase {

  @Test
  @DisplayName(
    "A user without kodekonveyor_sales role receives an Unauthorized status"
  )
  void test() {
    AuthenticatedUserServiceStubs
        .authenticated(authenticatedUserService);

    ThrowableTester.assertThrows(() -> listleadController.call())
        .assertException(UnauthorizedException.class);
  }

}
