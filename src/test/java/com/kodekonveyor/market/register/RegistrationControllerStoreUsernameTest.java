package com.kodekonveyor.market.register;

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
import com.kodekonveyor.authentication.AuthenticatedUserServiceStubs;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("store username")
@TestedService("RegistrationController")
public class RegistrationControllerStoreUsernameTest
    extends RegistrationControllerTestBase {

  @Test
  @DisplayName(
    "The user is stored if all the information is correct"
  )
  void test() {
    AuthenticatedUserServiceStubs.unregistered(authenticatedUserService);
    registrationController.call(MarketUserDTOTestData.getIdNull());
    verify(marketUserEntityRepository)
        .save(MarketUserEntityTestData.getIdNewlySaved());
  }

}
