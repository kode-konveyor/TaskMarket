package com.kodekonveyor.market.register;

import static org.junit.Assert.assertEquals;

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
import com.kodekonveyor.authentication.RoleEntityTestData;
import com.kodekonveyor.authentication.UserEntityTestData;
import com.kodekonveyor.exception.ThrowableTester;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("roles")
@TestedService("AddToRoleController")
public class AddToRoleControllerRolesTest extends AddToRoleControllerTestBase {

  @Test
  @DisplayName(
    "When the user registers itself with the github login name, it is added to the 'registered' role"
  )
  void test() {
    AuthenticatedUserStubs.canBePayed(authenticatedUserService);
    assertEquals(
        RoleEntityTestData.getNameRegistered(),
        UserEntityTestData.getRoleRegistered().getRoles().iterator().next()
    );

  }

  @Test
  @DisplayName(
    "if the user is registerd role, no exception is thrown"
  )
  void test2() {
    AuthenticatedUserStubs.canBePayed(authenticatedUserService);
    ThrowableTester.assertNoException(
        () -> addToRoleController
            .call(RegisterTestData.PROJECTNAME, RegisterTestData.PROJECTROLE)
    );
  }

  @Test
  @DisplayName(
    "if the login is null, we throw unregistered exception"
  )
  void test3() {
    AuthenticatedUserStubs.unregistered(authenticatedUserService);

    ThrowableTester.assertThrows(
        () -> addToRoleController
            .call(RegisterTestData.PROJECTNAME, RegisterTestData.PROJECTROLE)
    ).assertMessageIs(AddToRoleControllerTestData.UNREGISERED);
  }

}
