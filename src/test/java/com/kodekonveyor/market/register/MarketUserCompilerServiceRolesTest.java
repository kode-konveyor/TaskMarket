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
import com.kodekonveyor.market.UnauthorizedException;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("roles")
@TestedService("UpdateTasksController")
public class MarketUserCompilerServiceRolesTest
    extends MarketUserCompilerServiceTestBase {

  @Test
  @DisplayName(
    "if the user does not have can_be_paid role and looking for its own data, an Exception is thrown"
  )
  void test() {
    AuthenticatedUserServiceStubs.authenticated(authenticatedUserService);
    ThrowableTester.assertThrows(
        () -> marketUserCompilerService.call(MarketUserTestData.ID)
    ).assertMessageIs(
        RegisterConstants.NO_CAN_BE_PAID_ROLE
    );
  }

  @Test
  @DisplayName(
    "if the user does have can_be_paid role and looking for its own data, no Exception is thrown"
  )
  void test0() {
    AuthenticatedUserServiceStubs.registered(authenticatedUserService);
    ThrowableTester.assertNoException(
        () -> marketUserCompilerService.call(MarketUserTestData.ID)
    );
  }

  @Test
  @DisplayName(
    "if the user have contract role, no Exception is thrown"
  )
  void test1() {
    AuthenticatedUserServiceStubs
        .kodekonveyorContract(authenticatedUserService);
    ThrowableTester.assertNoException(
        () -> marketUserCompilerService
            .call(MarketUserTestData.ID_IS_TERMS_ACCEPTED_FALSE)
    );
  }

  @Test
  @DisplayName(
    "if the user is looking for another user and have no contract role, an UnauthorizedException is thrown"
  )
  void test2() {
    AuthenticatedUserServiceStubs.registered(authenticatedUserService);
    ThrowableTester.assertThrows(
        () -> marketUserCompilerService
            .call(MarketUserTestData.ID_IS_TERMS_ACCEPTED_FALSE)
    ).assertException(UnauthorizedException.class);
  }

  @Test
  @DisplayName(
    "if the user is looking for another user and have no contract role, the error message is 'foo'"
  )
  void test3() {
    AuthenticatedUserServiceStubs.registered(authenticatedUserService);
    ThrowableTester.assertThrows(
        () -> marketUserCompilerService
            .call(MarketUserTestData.ID_IS_TERMS_ACCEPTED_FALSE)
    ).assertMessageContains(MarketUserTestData.NO_CONTRACT_ROLE);
  }

}
