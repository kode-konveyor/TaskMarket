package com.kodekonveyor.market.register;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

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

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("store contract details")
@TestedService("RegistrationController")
public class RegistrationControllerStoreContractDetailsTest
    extends RegistrationControllerTestBase {

  @Test
  @DisplayName(
    "The user is stored if all the information is correct"
  )
  void test() {
    AuthenticatedUserStubs
        .authenticated(authenticatedUserService);
    registrationController.call(RegistrationInfoDTOTestData.get());
    verify(marketUserEntityRepository).save(MarketUserEntityTestData.get());
  }

  @Test
  @DisplayName(
    "The country is stored in repository"
  )
  void test1() {
    assertEquals(
        MarketUserEntityTestData.get().getLegal().getCountry(),
        UserLegalInfoEntityTestData.COUNTRY
    );
  }

  @Test
  @DisplayName(
    "The Legal name is stored in repository"
  )
  void test2() {
    assertEquals(
        MarketUserEntityTestData.get().getLegal().getLegalName(),
        UserLegalInfoEntityTestData.LEGALNAME
    );
  }

  @Test
  @DisplayName(
    "The Legal address is stored in repository"
  )
  void test3() {
    assertEquals(
        MarketUserEntityTestData.get().getLegal().getLegalAddress(),
        UserLegalInfoEntityTestData.ADDRESS
    );
  }

  @Test
  @DisplayName(
    "The email is stored in repository"
  )
  void test4() {
    assertEquals(
        MarketUserEntityTestData.get().getLegal().getEmail(),
        UserLegalInfoEntityTestData.EMAIL
    );
  }

  @Test
  @DisplayName(
    "The payment details is stored in repository"
  )
  void test5() {
    assertEquals(
        MarketUserEntityTestData.get().getLegal().getPaymentDetails(),
        UserLegalInfoEntityTestData.PAYMENT_DETAILS
    );
  }

  @Test
  @DisplayName(
    "The payment regime is stored in repository"
  )
  void test6() {
    assertEquals(
        MarketUserEntityTestData.get().getLegal().getPaymentRegime(),
        UserLegalInfoEntityTestData.PAYMENT_REGIME
    );
  }

}
