package com.kodekonveyor.market.register;

import com.kodekonveyor.annotations.TestedBehaviour;
import com.kodekonveyor.annotations.TestedService;
import com.kodekonveyor.authentication.AuthenticatedUserServiceStubs;
import com.kodekonveyor.authentication.RoleTestData;
import com.kodekonveyor.authentication.UserTestData;
import com.kodekonveyor.exception.ThrowableTester;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.startsWith;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("Logging")
@TestedService("AddToRoleController")
public class AddToRoleControllerLoggingTest extends AddToRoleControllerTestBase {

    @Test
    @DisplayName(
            "Start of execution for AddToRoleController is logged."
    )
    void test1() {
        AuthenticatedUserServiceStubs.projectManager(authenticatedUserService);

        addToRoleController.call(
                UserTestData.LOGIN_REGISTERED,
                RoleTestData.ID_KODEKONVEYOR_CONTRACT
        );

        Mockito.verify(loggerService)
                .info(
                        RegisterTestData.EXP_LOG_ADD_TO_ROLE_API_CALL,
                        UserTestData.LOGIN_REGISTERED,
                        RoleTestData.ID_KODEKONVEYOR_CONTRACT
                );
    }

    @Test
    @DisplayName(
            "End of execution for AddToRoleController is logged."
    )
    void test2() {
        AuthenticatedUserServiceStubs.projectManager(authenticatedUserService);

        addToRoleController.call(
                UserTestData.LOGIN_REGISTERED,
                RoleTestData.ID_KODEKONVEYOR_CONTRACT
        );

        Mockito.verify(loggerService)
                .debug(
                        RegisterTestData.EXP_LOG_ADD_TO_ROLE_API_SUCCES_CALL
                );
    }

    @Test
    @DisplayName(
            "Error is logged when user is not found."
    )
    void test3() {
        AuthenticatedUserServiceStubs.projectManager(authenticatedUserService);

        ThrowableTester.assertThrows(
                () -> addToRoleController
                        .call(UserTestData.LOGIN_BAD, RoleTestData.ID_PROJECT_MANAGER)
        );

        Mockito.verify(loggerService)
                .warn(
                        RegisterTestData.EXP_LOG_ADD_TO_ROLE_API_FAILURE_CALL,
                        UserTestData.LOGIN_BAD,
                        AddToRoleControllerTestData.UNREGISERED
                );
    }

    @Test
    @DisplayName(
            "Error is logged when the session user is not having role, manager."
    )
    void test4() {
        AuthenticatedUserServiceStubs.authenticated(authenticatedUserService);
        ThrowableTester.assertThrows(
                () -> addToRoleController
                        .call(
                                UserTestData.LOGIN_REGISTERED,
                                RoleTestData.ID_KODEKONVEYOR_CONTRACT
                        )
        );

        Mockito.verify(loggerService)
                .warn(
                        RegisterTestData.EXP_LOG_ADD_TO_ROLE_API_FAILURE_CALL,
                        UserTestData.LOGIN_REGISTERED,
                        RegisterTestData.NO_MANAGER_ROLE_FOR_THE_PROJECT
                );
    }

    @Test
    @DisplayName(
            "Error is logged when user to be added, does not has role can_be_paid."
    )
    void test() {
        AuthenticatedUserServiceStubs.projectManager(authenticatedUserService);

        ThrowableTester.assertThrows(
                () -> addToRoleController
                        .call(UserTestData.LOGIN, RoleTestData.ID_KODEKONVEYOR_CONTRACT)
        );

        Mockito.verify(loggerService)
                .warn(
                        eq(RegisterTestData.EXP_LOG_ADD_TO_ROLE_API_FAILURE_CALL),
                        eq(UserTestData.LOGIN),
                        startsWith(RoleTestData.NO_CAN_BE_PAID_ROLE_FOR_USER)
                );
    }

}
