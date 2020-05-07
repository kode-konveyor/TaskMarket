package com.kodekonveyor.market.project;

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
@TestedService("UpdateProjectModelController")
public class UpdateProjectModelControllerRolesTest
    extends UpdateTaskServiceTestBase {

  @Test
  @DisplayName(
    "if the user does not have Project manager role, a UnauthorizedException is thrown"
  )
  void test() {
    AuthenticatedUserServiceStubs.authenticated(authenticatedUserService);
    ThrowableTester.assertThrows(
        () -> updateTasksService.call(ModelExcerptDTOTestData.get().getTasks())
    ).assertException(UnauthorizedException.class);
  }

  @Test
  @DisplayName(
    "if the user has Project_manager role, no exception is thrown"
  )
  void test1() {
    AuthenticatedUserServiceStubs.projectManager(authenticatedUserService);

    ThrowableTester.assertNoException(
        () -> updateTasksService.call(ModelExcerptDTOTestData.get().getTasks())
    );
  }

}
