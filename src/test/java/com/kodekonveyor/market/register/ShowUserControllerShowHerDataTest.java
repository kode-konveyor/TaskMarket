package com.kodekonveyor.market.register;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
@TestedBehaviour("Show Her Data")
@TestedService("ShowUserController")
public class ShowUserControllerShowHerDataTest
    extends ShowUserControllerTestBase {

  @Test
  @DisplayName("The data of the currently authenticated user is shown")
  public void test() {
    AuthenticatedUserServiceStubs
        .authenticated(authenticatedUserService);
    assertEquals(MarketUserDTOTestData.get(), showUserController.call());
  }

  @Test
  @DisplayName(
    "The data of the currently authenticated user is shown with empty set even if the database returns nulls"
  )
  public void test2() {
    AuthenticatedUserServiceStubs
        .unregistered(authenticatedUserService);
    assertEquals(
        MarketUserDTOTestData.getIdNotInDatabase(),
        showUserController.call()
    );
  }

}
