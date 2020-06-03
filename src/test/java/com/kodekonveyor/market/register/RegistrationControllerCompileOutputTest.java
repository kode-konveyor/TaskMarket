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

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("compile output")
@TestedService("RegistrationController")
public class RegistrationControllerCompileOutputTest
    extends RegistrationControllerTestBase {

  @Test
  @DisplayName("Market user data is provided correctly")
  public void test() {

    assertEquals(
        MarketUserDTOTestData.get(),
        registrationController.call(MarketUserDTOTestData.get())
    );

  }

}
