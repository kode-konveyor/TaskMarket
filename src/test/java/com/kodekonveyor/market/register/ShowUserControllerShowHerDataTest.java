package com.kodekonveyor.market.register;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
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
@TestedBehaviour("Show Her Data")
@TestedService("ShowUserController")
public class ShowUserControllerShowHerDataTest
    extends ShowUserControllerTestBase {

  MarketUserDTOTestData registerTestData;

  @BeforeEach
  void setUp() {
    MarketUserStubs
        .behaviour(marketUserEntityRepository, registerTestData);
  }

  @Test
  @DisplayName("The data of the currently authenticated user is shown")
  public void test() {
    AuthenticatedUserStubs
        .authenticated(authenticatedUserService);
    assertEquals(MarketUserDTOTestData.get(), showUserController.call());
  }

  @Test
  @DisplayName("If there is no MarketUserEntity for the user, it is created")
  public void test1() {
    AuthenticatedUserStubs.noMarketuser(authenticatedUserService);
    assertEquals(
        MarketUserDTOTestData.getLoginNoMarket(),
        showUserController.call()
    );
  }
}
