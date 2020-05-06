package com.kodekonveyor.market.register;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.kodekonveyor.annotations.TestedBehaviour;
import com.kodekonveyor.annotations.TestedService;
import com.kodekonveyor.authentication.AuthenticatedUserServiceStubs;
import com.kodekonveyor.authentication.RoleEntity;
import com.kodekonveyor.authentication.RoleTestData;
import com.kodekonveyor.authentication.UserEntity;
import com.kodekonveyor.authentication.UserTestData;
import com.kodekonveyor.exception.ThrowableTester;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("one role once")
@TestedService("AddToRoleController")
public class AddToRoleControllerOneRoleOnceTest
    extends AddToRoleControllerTestBase {

  @Test
  @DisplayName(
    "When the user can be payed & registered , it is added to the role"
  )
  void test() {
    AuthenticatedUserServiceStubs.projectManager(authenticatedUserService);
    addToRoleController
        .call(
            UserTestData.LOGIN_REGISTERED, RoleTestData.ID_KODEKONVEYOR_CONTRACT
        );
    final ArgumentCaptor<UserEntity> entity =
        ArgumentCaptor.forClass(UserEntity.class);
    verify(userEntityRepository).save(entity.capture());
    assertTrue(
        entity.getValue().getRole().stream()
            .map(RoleEntity::getId)
            .filter(RoleTestData.ID_KODEKONVEYOR_CONTRACT::equals)
            .findFirst().isPresent()
    );
  }

  @Test
  @DisplayName(
    "if the user already have the role, no exception is thrown"
  )
  void test2() {
    AuthenticatedUserServiceStubs.projectManager(authenticatedUserService);
    ThrowableTester.assertNoException(
        () -> addToRoleController
            .call(
                UserTestData.LOGIN_CONTRACT,
                RoleTestData.ID_KODEKONVEYOR_CONTRACT
            )
    );
  }

}
