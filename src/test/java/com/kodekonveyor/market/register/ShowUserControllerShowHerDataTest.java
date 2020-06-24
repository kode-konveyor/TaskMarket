package com.kodekonveyor.market.register;

import static com.kodekonveyor.authentication.UserTestData.LOGIN;
import static com.kodekonveyor.authentication.UserTestData.LOGIN_NO_MARKET_USER;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.kodekonveyor.authentication.UserEntityRepositoryStubs;
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
    assertEquals(MarketUserDTOTestData.get(), showUserController.call(LOGIN));
  }

  @Test
  @DisplayName(
    "The data of the currently authenticated user is shown with empty set even if the database returns nulls"
  )
  public void test2() {
    AuthenticatedUserServiceStubs
        .unregistered(authenticatedUserService);
    UserEntityRepositoryStubs.behaviour(userEntityRepository);
    assertEquals(
        MarketUserDTOTestData.getIdNotInDatabase(),
        showUserController.call(LOGIN_NO_MARKET_USER)
    );
  }

}
