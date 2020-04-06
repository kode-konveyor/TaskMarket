package com.kodekonveyor.market.lead;

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
import com.kodekonveyor.exception.ThrowableTester;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("input validation")
@TestedService("RegisterInterestController")
public class RegisterInterestControllerInputValidationTest
    extends RegisterInterestControllerTestBase {

  @Test
  @DisplayName("When email format is incorrect, we throw an exception")
  public void emailFormattest() {
    ThrowableTester.assertThrows(
        () -> registerInterestController.call(LeadDTOTestData.getEmailInvalid())
    )

        .assertMessageIs(
            LeadDTOValidationUtilTestData.INVALID_EMAIL_FORMAT_EXCEPTION
        );

  }

  @Test
  @DisplayName("When email is null, we throw an exception")
  public void emailNotNulltest() {

    ThrowableTester.assertThrows(
        () -> registerInterestController.call(LeadDTOTestData.getEmailNull())
    )

        .assertMessageIs(LeadDTOValidationUtilTestData.EMAIL_NULL_EXCEPTION);

  }

  @Test
  @DisplayName("When first name format incorrect, we throw an exception")
  public void firstNameForamttest() {

    ThrowableTester.assertThrows(
        () -> registerInterestController
            .call(LeadDTOTestData.getfirstNameInvaliidFormat())
    )

        .assertMessageIs(
            LeadDTOValidationUtilTestData.FIRST_NAME_INVALID_EXCEPTION
        );

  }

  @Test
  @DisplayName("When first name is null, we throw an exception")
  public void firstNameNotNulltest() {

    ThrowableTester.assertThrows(
        () -> registerInterestController.call(LeadDTOTestData.getNameNull())
    )

        .assertMessageIs(LeadDTOValidationUtilTestData.FIRST_NAME_NULL_EXCEPTION);

  }

  @Test
  @DisplayName("When Interest is null, we throw an exception")
  public void interestNotNulltest() {

    ThrowableTester.assertThrows(
        () -> registerInterestController.call(LeadDTOTestData.getInterestNull())
    )

        .assertMessageIs(LeadDTOValidationUtilTestData.INTEREST_NULL_EXCEPTION);

  }

}
