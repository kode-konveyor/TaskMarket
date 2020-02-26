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
@TestedService("CreateProjectController")
public class CreateProjectControllerInputValidationTest
    extends CreateProjectControllerTestBase {

  @Test
  @DisplayName("When project Id is non positive, we throw an exception")
  public void projectIdtest() {

    ThrowableTester.assertThrows(
        () -> createProjectController.call(ProjectDTOTestData.getNonPositiveId())
    )

        .assertMessageIs(
            CreateProjectControllerTestData.PROJECT_ID_NON_POSITIVE_EXCEPTION
        );
  }

  @Test
  @DisplayName("When project name is invalid, we throw an exception")
  public void projectNameInvalidtest() {

    ThrowableTester.assertThrows(
        () -> createProjectController.call(ProjectDTOTestData.getInvalidName())
    )

        .assertMessageIs(
            CreateProjectControllerTestData.PROJECT_NAME_INVALID_EXCEPTION
        );
  }

  @Test
  @DisplayName("When project name is null, we throw an exception")
  public void projectNameNulltest() {

    ThrowableTester.assertThrows(
        () -> createProjectController.call(ProjectDTOTestData.getNullName())
    )

        .assertMessageIs(
            CreateProjectControllerTestData.PROJECT_NAME_NULL_EXCEPTION
        );
  }

  @Test
  @DisplayName("When project parameters are vaid no exception is thrown")
  public void validProjecttest() {
    createProjectController.call(ProjectDTOTestData.get());
    ThrowableTester.assertNoException(
        () -> createProjectController.call(ProjectDTOTestData.get())
    );
  }
}
