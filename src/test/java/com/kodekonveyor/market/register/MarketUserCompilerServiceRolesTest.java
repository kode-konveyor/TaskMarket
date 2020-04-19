package com.kodekonveyor.market.register;

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
import com.kodekonveyor.authentication.AuthenticatedUserStubs;
import com.kodekonveyor.authentication.UserEntityTestData;
import com.kodekonveyor.exception.ThrowableTester;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("roles")
@TestedService("UpdateTasksController")
public class MarketUserCompilerServiceRolesTest
    extends MarketUserCompilerServiceTestBase {

  @Test
  @DisplayName(
    "if the user does not have registered role, an Exception is thrown"
  )
  void test() {
    AuthenticatedUserStubs.canBePayed(authenticatedUserService);
    ThrowableTester.assertThrows(
        () -> marketUserCompilerService.call(UserEntityTestData.ID)
    ).assertMessageIs(
        RegisterConstants.UNAUTHORIZED_NOT_ENOUGH_RIGHTS
    );
  }

  @Test
  @DisplayName(
    "if the user havs registered role, no Exception is thrown"
  )
  void test1() {
    AuthenticatedUserStubs.registered(authenticatedUserService);
    ThrowableTester.assertNoException(
        () -> marketUserCompilerService.call(UserEntityTestData.ID)
    );
  }

}
