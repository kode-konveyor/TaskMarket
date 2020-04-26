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
import com.kodekonveyor.authentication.AuthenticatedUserServiceStubs;
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
    "if the user does not have contract role, an Exception is thrown"
  )
  void test() {
    AuthenticatedUserServiceStubs.registered(authenticatedUserService);
    ThrowableTester.assertThrows(
        () -> marketUserCompilerService.call(MarketUserTestData.ID)
    ).assertMessageIs(
        RegisterConstants.NO_CAN_BE_PAID_ROLE
    );
  }

  @Test
  @DisplayName(
    "if the user have contract role, no Exception is thrown"
  )
  void test1() {
    AuthenticatedUserServiceStubs.kodekonveyorContract(authenticatedUserService);
    ThrowableTester.assertNoException(
        () -> marketUserCompilerService.call(MarketUserTestData.ID)
    );
  }

}
