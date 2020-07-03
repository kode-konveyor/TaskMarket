package com.kodekonveyor.market.project;

import com.kodekonveyor.annotations.TestedBehaviour;
import com.kodekonveyor.annotations.TestedService;
import com.kodekonveyor.authentication.AuthenticatedUserServiceStubs;
import com.kodekonveyor.exception.ThrowableTester;
import com.kodekonveyor.market.UnauthorizedException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static com.kodekonveyor.market.project.ProjectTestData.EXPECTED_AUTH_ERROR_FOR_UPDATE_PROJECT;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("roles")
@TestedService("UpdateProjectModelController")
public class UpdateProjectModelControllerRolesTest extends UpdateProjectModelControllerTestBase {

    @Test
    @DisplayName("If the user does not have manager role for the project, the access is denied and exception is thrown.")
    public void test1() {
        AuthenticatedUserServiceStubs.salesUser(authenticatedUserService);

        ThrowableTester.assertThrows(
                () -> updateProjectModelController.call(
                        ProjectModelDTOTestData.get(), ProjectTestData.NAME_KODE_KONVEYOR
                )
        )
                .assertException(UnauthorizedException.class);
    }

    @Test
    @DisplayName(
            "If the user does not have manager role for the project, error message is : Project model can only be modified by project manager."
    )
    public void test2() {
        AuthenticatedUserServiceStubs.salesUser(authenticatedUserService);

        ThrowableTester.assertThrows(
                () -> updateProjectModelController.call(
                        ProjectModelDTOTestData.get(), ProjectTestData.NAME_KODE_KONVEYOR
                )
        )
                .assertMessageIs(EXPECTED_AUTH_ERROR_FOR_UPDATE_PROJECT);
    }

    @Test
    @DisplayName("If the user has manager role for the project, no exception is thrown.")
    public void test3() {
        ThrowableTester.assertNoException(
                () -> updateProjectModelController.call(
                        ProjectModelDTOTestData.get(), ProjectTestData.PROJECT_NAME
                )
        );
    }

}
