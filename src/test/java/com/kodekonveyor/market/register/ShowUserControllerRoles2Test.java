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
public class ShowUserControllerRoles2Test extends ShowUserControllerTestBase {

    @Test
    @DisplayName("Payment details is not returned, when session user has role - technical.")
    public void test11() {
        AuthenticatedUserServiceStubs.technicalUser(authenticatedUserService);
        assertNull(showUserController.call(LOGIN).getPaymentDetail());
    }

    @Test
    @DisplayName("Legal name is returned successfully, when session user has role - contract.")
    public void test12() {
        AuthenticatedUserServiceStubs
                .kodekonveyorContract(authenticatedUserService);
        assertEquals(
                MarketUserDTOTestData.get().getLegalName(),
                showUserController.call(LOGIN).getLegalName()
        );
    }

    @Test
    @DisplayName("BalanceInCents is not returned, when session user has role - contract.")
    public void test13() {
        AuthenticatedUserServiceStubs
                .kodekonveyorContract(authenticatedUserService);
        assertNull(
                showUserController.call(LOGIN).getBalanceInCents()
        );
    }

    @Test
    @DisplayName("LegalForm is returned successfully, when session user has role - contract.")
    public void test14() {
        AuthenticatedUserServiceStubs.kodekonveyorContract(authenticatedUserService);
        assertEquals(
                MarketUserDTOTestData.get().getLegalForm(),
                showUserController.call(LOGIN).getLegalForm()
        );
    }

    @Test
    @DisplayName("LegalAddress is returned successfully, when session user has role - contract.")
    public void test15() {
        AuthenticatedUserServiceStubs.kodekonveyorContract(authenticatedUserService);
        assertEquals(
                MarketUserDTOTestData.get().getLegalAddress(),
                showUserController.call(LOGIN).getLegalAddress()
        );
    }

    @Test
    @DisplayName("IsTermsAccepted is returned successfully, when session user has role - contract.")
    public void test16() {
        AuthenticatedUserServiceStubs.kodekonveyorContract(authenticatedUserService);
        assertEquals(
                MarketUserDTOTestData.get().getIsTermsAccepted(),
                showUserController.call(LOGIN).getIsTermsAccepted()
        );
    }

    @Test
    @DisplayName("Payment details are returned successfully, when session user has role - contract.")
    public void test17() {
        AuthenticatedUserServiceStubs.kodekonveyorContract(authenticatedUserService);
        assertEquals(
                MarketUserDTOTestData.get().getPaymentDetail(),
                showUserController.call(LOGIN).getPaymentDetail()
        );
    }

    @Test
    @DisplayName("PersonalName is returned successfully, when session user has role - contract.")
    public void test18() {
        AuthenticatedUserServiceStubs.kodekonveyorContract(authenticatedUserService);
        assertEquals(
                MarketUserDTOTestData.get().getPersonalName(),
                showUserController.call(LOGIN).getPersonalName()
        );
    }

    @Test
    @DisplayName("Email is returned successfully, when session user has role - contract.")
    public void test19() {
        AuthenticatedUserServiceStubs.kodekonveyorContract(authenticatedUserService);
        assertEquals(
                MarketUserDTOTestData.get().getEmail(),
                showUserController.call(LOGIN).getEmail()
        );
    }

    @Test
    @DisplayName("Legal name is returned successfully, when session user is itself input.")
    public void test20() {
        AuthenticatedUserServiceStubs.registered(authenticatedUserService);
        assertEquals(
                MarketUserDTOTestData.getRoleCanBePaid().getLegalName(),
                showUserController.call(LOGIN_REGISTERED).getLegalName()
        );
    }
}
