package com.kodekonveyor.market.register;

import static org.junit.Assert.assertEquals;

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
@TestedBehaviour("compile output")
@TestedService("ShowUserController")
public class ShowUserControllerCompileOutput2Test
    extends ShowUserControllerTestBase {

  @Test
  @DisplayName("The Market user's personal name is returned successfully")
  public void test7() {
    AuthenticatedUserServiceStubs
        .authenticated(authenticatedUserService);
    assertEquals(
        MarketUserDTOTestData.get().getPersonalName(),
        showUserController.call().getPersonalName()
    );
  }

  @Test
  @DisplayName("The Market user's legal form is returned successfully")
  public void test8() {
    AuthenticatedUserServiceStubs
        .authenticated(authenticatedUserService);
    assertEquals(
        MarketUserDTOTestData.get().getLegalForm(),
        showUserController.call().getLegalForm()
    );
  }

  @Test
  @DisplayName("The Market user's userId is returned successfully")
  public void test9() {
    AuthenticatedUserServiceStubs
        .authenticated(authenticatedUserService);
    assertEquals(
        MarketUserDTOTestData.get().getUser(),
        showUserController.call().getUser()
    );
  }

  @Test
  @DisplayName(
    "The Market user's payment details Ids are returned successfully"
  )
  public void test11() {
    AuthenticatedUserServiceStubs
        .authenticated(authenticatedUserService);
    assertEquals(
        MarketUserDTOTestData.get().getPaymentDetail(),
        showUserController.call().getPaymentDetail()
    );
  }
}
