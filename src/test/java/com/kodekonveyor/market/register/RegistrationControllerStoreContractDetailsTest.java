package com.kodekonveyor.market.register;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.kodekonveyor.annotations.TestedBehaviour;
import com.kodekonveyor.annotations.TestedService;
import com.kodekonveyor.authentication.AuthenticatedUserServiceStubs;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("store contract details")
@TestedService("RegistrationController")
public class RegistrationControllerStoreContractDetailsTest
    extends RegistrationControllerTestBase {

  @Test
  @DisplayName(
    "The Market user is saved successfully in repository"
  )
  void test() {
    AuthenticatedUserServiceStubs.unregistered(authenticatedUserService);
    registrationController.call(MarketUserDTOTestData.getIdNull());
    Mockito.verify(marketUserEntityRepository)
        .save(MarketUserEntityTestData.getIdNewlySaved());
  }

  @Test
  @DisplayName(
    "The Market user's Id is saved successfully in repository"
  )
  void test1() {
    registrationController.call(MarketUserDTOTestData.getIdNull());
    assertEquals(
        MarketUserTestData.ID_NOT_IN_DATABASE,
        MarketUserEntityTestData.getIdNewlySaved().getId()
    );

  }

  @Test
  @DisplayName(
    "The Market user's balance is saved successfully in repository"
  )
  void test2() {
    registrationController.call(MarketUserDTOTestData.getIdNull());
    assertEquals(
        MarketUserTestData.ZERO_BALANCE,
        MarketUserEntityTestData.getIdNewlySaved().getBalanceInCents()
    );

  }

  @Test
  @DisplayName(
    "The Market user's email is saved successfully in repository"
  )
  void test3() {
    registrationController.call(MarketUserDTOTestData.getIdNull());
    assertEquals(
        MarketUserDTOTestData.get().getEmail(),
        MarketUserEntityTestData.getIdNewlySaved().getEmail()
    );

  }

  @Test
  @DisplayName(
    "The Market user's terms acceptance status successfully in repository"
  )
  void test4() {
    registrationController.call(MarketUserDTOTestData.getIdNull());
    assertEquals(
        MarketUserDTOTestData.get().getIsTermsAccepted(),
        MarketUserEntityTestData.getIdNewlySaved().getIsTermsAccepted()
    );
  }

  @Test
  @DisplayName(
    "The Market user's legal address is saved successfully in repository"
  )
  void test5() {
    registrationController.call(MarketUserDTOTestData.getIdNull());
    assertEquals(
        MarketUserDTOTestData.get().getLegalAddress(),
        MarketUserEntityTestData.getIdNewlySaved().getLegalAddress()
    );

  }

  @Test
  @DisplayName(
    "The Market user's legal name is saved successfully in repository"
  )
  void test6() {
    registrationController.call(MarketUserDTOTestData.getIdNull());
    assertEquals(
        MarketUserDTOTestData.get().getLegalName(),
        MarketUserEntityTestData.getIdNewlySaved().getLegalName()
    );

  }

}
