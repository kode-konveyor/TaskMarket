package com.kodekonveyor.market.project;

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
@TestedService("AddFundsToProjectController")
public class AddFundsToProjectControllerInputValidationTest
    extends AddFundsToProjectControllerTestBase {

  @Test
  @DisplayName("When project Id is just positive, we throw no exception")
  public void projectId1test() {

    ThrowableTester.assertNoException(
        () -> addFundsToProjectController
            .call(
                ProjectTestData.ID_ADD_FUNDS,
                ProjectTestData.BUDGET_IN_LESSER_AMOUNT
            )
    );
  }

  @Test
  @DisplayName("When project Id is negative, we throw an exception")
  public void projectIdtest() {

    ThrowableTester.assertThrows(
        () -> addFundsToProjectController
            .call(ProjectTestData.NEGATIVE_ID, ProjectTestData.BUDGET_IN_CENTS)
    )

        .assertMessageIs(
            ProjectTestData.PROJECT_ID_NON_POSITIVE_EXCEPTION
        );
  }

  @Test
  @DisplayName("When project Id is zero, we throw an exception")
  public void projectIdZerotest() {
    ThrowableTester.assertThrows(
        () -> addFundsToProjectController
            .call(ProjectTestData.ID_ZERO, ProjectTestData.BUDGET_IN_CENTS)
    ).assertMessageIs(
        ProjectTestData.PROJECT_ID_NON_POSITIVE_EXCEPTION
    );
  }
}
