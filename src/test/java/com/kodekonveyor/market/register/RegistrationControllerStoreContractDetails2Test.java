package com.kodekonveyor.market.register;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

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
import com.kodekonveyor.authentication.UserEntityTestData;
import com.kodekonveyor.market.payment.LegalFormEntityTestData;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("store contract details")
@TestedService("RegistrationController")
public class RegistrationControllerStoreContractDetails2Test
    extends RegistrationControllerTestBase {

  @Test
  @DisplayName(
    "The Market user's personal name is saved successfully in repository"
  )
  void test7() {
    registrationController.call(MarketUserDTOTestData.getIdNull());
    assertEquals(
        MarketUserDTOTestData.get().getPersonalName(),
        MarketUserEntityTestData.getIdNewlySaved().getPersonalName()
    );

  }

  @Test
  @DisplayName(
    "The Market user's legal form is saved successfully in repository"
  )
  void test8() {
    registrationController.call(MarketUserDTOTestData.getIdNull());
    assertEquals(
        LegalFormEntityTestData.get(),
        MarketUserEntityTestData.getIdNewlySaved().getLegalForm()
    );

  }

  @Test
  @DisplayName(
    "The Market user's user details are saved successfully in repository"
  )
  void test9() {
    registrationController.call(MarketUserDTOTestData.getIdNull());
    assertEquals(
        UserEntityTestData.getIdNoMarketUser(),
        MarketUserEntityTestData.getIdNewlySaved().getUser()
    );

  }

  @Test
  @DisplayName(
    "Empty payment details set created for Market user and saved successfully in repository"
  )
  void test11() {
    registrationController.call(MarketUserDTOTestData.getIdNull());
    assertEquals(
        Set.of(),
        MarketUserEntityTestData.getIdNewlySaved().getPaymentDetail()
    );

  }

}
