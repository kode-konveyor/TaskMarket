package com.kodekonveyor.market.register;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.web.servlet.view.RedirectView;

import com.kodekonveyor.annotations.TestedBehaviour;
import com.kodekonveyor.annotations.TestedService;
import com.kodekonveyor.authentication.UserTestData;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("redirect for login")
@TestedService("LoginController")
public class LoginControllerTest {

  @InjectMocks
  LoginController loginController;

  @Test
  @DisplayName("The next parameter is embedded in the return value")
  public void test() {
    final UserTestData userTestData = new UserTestData();
    final RegisterTestData registerTestData =
        new RegisterTestData(userTestData);
    final RedirectView redirectView =
        loginController.call(registerTestData.NEXT_URL);
    assertEquals(
        registerTestData.NEXT_URL, redirectView.getUrl()
    );
  }
}
