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
import org.springframework.web.servlet.view.RedirectView;

import com.kodekonveyor.annotations.TestedBehaviour;
import com.kodekonveyor.annotations.TestedService;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("redirect for login")
@TestedService("LoginController")
public class LoginControllerRedirectForLoginTest extends LoginControllerTestBase {

  @Test
  @DisplayName("The next parameter is embedded in the return value")
  public void test() {
    final RedirectView redirectView =
        loginController.call(LoginControllerTestData.NEXT_URL);
    assertEquals(
        LoginControllerTestData.NEXT_URL, redirectView.getUrl()
    );
  }

}
