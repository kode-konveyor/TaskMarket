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
import static com.kodekonveyor.authentication.UserTestData.LOGIN_REGISTERED;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("Roles")
@TestedService("ShowUserController")
public class ShowUserControllerRoles3Test extends ShowUserControllerTestBase {

    @Test
    @DisplayName("BalanceInCents is returned successfully, when session user is itself input.")
    public void test21() {
        AuthenticatedUserServiceStubs.registered(authenticatedUserService);
        assertEquals(
                MarketUserDTOTestData.getRoleCanBePaid().getBalanceInCents(),
                showUserController.call(LOGIN_REGISTERED).getBalanceInCents()
        );
    }

    @Test
    @DisplayName("LegalForm is returned successfully, when session user is itself input.")
    public void test22() {
        AuthenticatedUserServiceStubs.registered(authenticatedUserService);
        assertEquals(
                MarketUserDTOTestData.getRoleCanBePaid().getLegalForm(),
                showUserController.call(LOGIN_REGISTERED).getLegalForm()
        );
    }

    @Test
    @DisplayName("LegalAddress is returned successfully, when session user is itself input.")
    public void test23() {
        AuthenticatedUserServiceStubs.registered(authenticatedUserService);
        assertEquals(
                MarketUserDTOTestData.getRoleCanBePaid().getLegalAddress(),
                showUserController.call(LOGIN_REGISTERED).getLegalAddress()
        );
    }

    @Test
    @DisplayName("IsTermsAccepted is returned successfully, when session user is itself input.")
    public void test24() {
        AuthenticatedUserServiceStubs.registered(authenticatedUserService);
        assertEquals(
                MarketUserDTOTestData.getRoleCanBePaid().getIsTermsAccepted(),
                showUserController.call(LOGIN_REGISTERED).getIsTermsAccepted()
        );
    }

    @Test
    @DisplayName("PersonalName is returned successfully, when session user is itself input.")
    public void test25() {
        AuthenticatedUserServiceStubs.registered(authenticatedUserService);
        assertEquals(
                MarketUserDTOTestData.getRoleCanBePaid().getPersonalName(),
                showUserController.call(LOGIN_REGISTERED).getPersonalName()
        );
    }

    @Test
    @DisplayName("Email is returned successfully, when session user is itself input.")
    public void test26() {
        AuthenticatedUserServiceStubs.registered(authenticatedUserService);
        assertEquals(
                MarketUserDTOTestData.getRoleCanBePaid().getEmail(),
                showUserController.call(LOGIN_REGISTERED).getEmail()
        );
    }

    @Test
    @DisplayName("Payment details are returned successfully, when session user is itself input.")
    public void test27() {
        AuthenticatedUserServiceStubs.kodekonveyorContract(authenticatedUserService);
        assertEquals(
                MarketUserDTOTestData.getRoleCanBePaid().getPaymentDetail(),
                showUserController.call(LOGIN_REGISTERED).getPaymentDetail()
        );
    }

    @Test
    @DisplayName(
            "Legal name is not returned, when session user is not itself input and does not have role - technical or contract."
    )
    public void test28() {
        AuthenticatedUserServiceStubs.registered(authenticatedUserService);
        assertNull(showUserController.call(LOGIN).getLegalName());
    }

    @Test
    @DisplayName(
            "BalanceInCents is not returned, when session user is not itself input and does not have role - technical or contract."
    )
    public void test29() {
        AuthenticatedUserServiceStubs.registered(authenticatedUserService);
        assertNull(showUserController.call(LOGIN).getBalanceInCents());
    }

    @Test
    @DisplayName(
            "LegalForm is not returned, when session user is not itself input and does not have role - technical or contract."
    )
    public void test30() {
        AuthenticatedUserServiceStubs.registered(authenticatedUserService);
        assertNull(showUserController.call(LOGIN).getLegalForm());
    }
}
