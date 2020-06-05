package com.kodekonveyor.market.project;

import com.kodekonveyor.annotations.TestedBehaviour;
import com.kodekonveyor.annotations.TestedService;
import com.kodekonveyor.authentication.AuthenticatedUserServiceStubs;
import com.kodekonveyor.exception.ThrowableTester;
import com.kodekonveyor.market.MarketConstants;
import com.kodekonveyor.market.UnauthorizedException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("roles")
@TestedService("UpdateProjectModelController")
public class UpdateProjectModelControllerRolesTest extends UpdateProjectModelControllerTestBase {

    @Test
    @DisplayName("Making call to update project model with unauthorized user.")
    public void test1() {
        AuthenticatedUserServiceStubs.salesUser(authenticatedUserService);

        ThrowableTester.assertThrows(
                () -> updateProjectModelController.call(
                        ProjectModelDTOTestData.get(), ProjectTestData.NAME_KODE_KONVEYOR
                )
        )
                .assertException(UnauthorizedException.class)
                .assertMessageIs(MarketConstants.UNAUTHORIZED_PROJECT_MODIFICATION);
    }

}
