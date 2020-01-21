package com.kodekonveyor.market.register;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
