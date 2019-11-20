package com.kodekonveyor.market.register;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.kodekonveyor.annotations.TestedBehaviour;
import com.kodekonveyor.annotations.TestedService;
import com.kodekonveyor.authentication.AuthenticatedUserService;
import com.kodekonveyor.authentication.UserTestData;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("Show Her Data")
@TestedService("ShowUserController")
public class ShowUserShowHerDataTest {

  @InjectMocks
  private ShowUserController showUserController;

  @Mock
  private AuthenticatedUserService authenticatedUserService;

  @Mock
  private MarketUserEntityRepository marketUserEntityRepository;

  @Test
  @DisplayName("The data of the currently authenticated user is shown")
  public void test() {
    final UserTestData userTestData = new UserTestData();
    final RegisterTestData registerTestData =
        new RegisterTestData(userTestData);
    doReturn(userTestData.TEST_USER_ENTITY).when(authenticatedUserService)
        .call();
    doReturn(List.of(registerTestData.MARKET_USER))
        .when(marketUserEntityRepository)
        .findByLogin(userTestData.TEST_USER_ENTITY);
    assertEquals(registerTestData.MARKET_USER_DTO, showUserController.call());
  }
}
