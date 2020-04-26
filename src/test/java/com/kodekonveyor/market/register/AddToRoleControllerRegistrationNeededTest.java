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
import com.kodekonveyor.authentication.RoleTestData;
import com.kodekonveyor.authentication.UserTestData;
import com.kodekonveyor.exception.ThrowableTester;
import com.kodekonveyor.market.UnauthorizedException;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("registration needed")
@TestedService("AddToRoleController")
public class AddToRoleControllerRegistrationNeededTest
    extends AddToRoleControllerTestBase {

  @Test
  @DisplayName(
    "if the user does not have can_be_payed role, a UnauthorizedException is thrown"
  )
  void test() {
    AuthenticatedUserServiceStubs.projectManager(authenticatedUserService);
    ThrowableTester.assertThrows(
        () -> addToRoleController
            .call(
                UserTestData.LOGIN,
                RoleTestData.ID_KODEKONVEYOR_CONTRACT
            )
    ).assertException(UnauthorizedException.class);
  }

  @Test
  @DisplayName(
    "if the user does not have can_be_payed role, the message is 'Unregistered'"
  )
  void test1() {
    AuthenticatedUserServiceStubs.projectManager(authenticatedUserService);

    ThrowableTester.assertThrows(
        () -> addToRoleController
            .call(UserTestData.LOGIN, RoleTestData.ID_KODEKONVEYOR_CONTRACT)
    )
        .assertMessageContains(RoleTestData.NO_CAN_BE_PAID_ROLE_FOR_USER);
  }

  @Test
  @DisplayName("if the user has can_be_paid role, no exception is thrown")
  void test2() {
    AuthenticatedUserServiceStubs.projectManager(authenticatedUserService);

    ThrowableTester.assertNoException(
        () -> addToRoleController
            .call(
                UserTestData.LOGIN_REGISTERED,
                RoleTestData.ID_KODEKONVEYOR_CONTRACT
            )
    );
  }

}
