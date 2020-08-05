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
@TestedService("ListRegisteredUsersController")
public class ListRegisteredUsersControllerRolesTest
    extends ListRegisteredUsersControllerTestBase {

  @Test
  @DisplayName(
    "An exception is thrown when unsupported role user is logged in"
  )
  public void testNoRole() {
    AuthenticatedUserServiceStubs
        .registered(authenticatedUserService);
    ThrowableTester.assertThrows(() -> listRegisteredUsersController.call())
        .assertMessageContains(RegisterTestData.NO_VALID_ROLE);

  }

  @Test
  @DisplayName(
    "An exception is thrown when unsupported role user is logged in"
  )
  public void testContractRole() {
    AuthenticatedUserServiceStubs
        .kodekonveyorContract(authenticatedUserService);
    listRegisteredUsersController.call();

  }
}
