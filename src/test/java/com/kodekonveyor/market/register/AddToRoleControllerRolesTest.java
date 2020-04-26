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
import com.kodekonveyor.market.ValidationException;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("roles")
@TestedService("AddToRoleController")
public class AddToRoleControllerRolesTest extends AddToRoleControllerTestBase {

  @Test
  @DisplayName(
    "if the calling user have manager role for the project, no exception is thrown"
  )
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

  @Test
  @DisplayName(
    "if the login is null, we throw ValidationException"
  )
  void test3() {
    AuthenticatedUserServiceStubs.unregistered(authenticatedUserService);

    ThrowableTester.assertThrows(
        () -> addToRoleController
            .call(UserTestData.LOGIN_BAD, RoleTestData.ID_PROJECT_MANAGER)
    ).assertException(ValidationException.class)
        .assertMessageIs(AddToRoleControllerTestData.UNREGISERED);
  }

}
