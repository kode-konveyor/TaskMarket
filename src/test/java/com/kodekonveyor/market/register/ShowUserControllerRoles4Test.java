package com.kodekonveyor.market.register;

import com.kodekonveyor.annotations.TestedBehaviour;
import com.kodekonveyor.annotations.TestedService;
import com.kodekonveyor.authentication.AuthenticatedUserServiceStubs;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static com.kodekonveyor.authentication.UserTestData.LOGIN;
import static org.junit.Assert.assertNull;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("Roles")
@TestedService("ShowUserController")
public class ShowUserControllerRoles4Test extends ShowUserControllerTestBase {

    @Test
    @DisplayName(
            "LegalAddress is not returned, when session user is not itself input and does not have role technical or contract."
    )
    public void test31() {
        AuthenticatedUserServiceStubs.registered(authenticatedUserService);
        assertNull(showUserController.call(LOGIN).getLegalAddress());
    }

    @Test
    @DisplayName(
            "IsTermsAccepted is not returned, when session user is not itself input and does not have role technical or contract."
    )
    public void test32() {
        AuthenticatedUserServiceStubs
                .registered(authenticatedUserService);
        assertNull(showUserController.call(LOGIN).getIsTermsAccepted());
    }

    @Test
    @DisplayName(
            "PersonalName is not returned, when session user is not itself input and does not have role technical or contract."
    )
    public void test33() {
        AuthenticatedUserServiceStubs.registered(authenticatedUserService);
        assertNull(showUserController.call(LOGIN).getPersonalName());
    }

    @Test
    @DisplayName(
            "Email is not returned, when session user is not itself input and does not have role technical or contract."
    )
    public void test34() {
        AuthenticatedUserServiceStubs.registered(authenticatedUserService);
        assertNull(showUserController.call(LOGIN).getEmail());
    }

    @Test
    @DisplayName(
            "Payment details are not returned, when session user is not itself input and does not have role technical or contract."
    )
    public void test35() {
        AuthenticatedUserServiceStubs.registered(authenticatedUserService);
        assertNull(showUserController.call(LOGIN).getPaymentDetail());
    }
}
