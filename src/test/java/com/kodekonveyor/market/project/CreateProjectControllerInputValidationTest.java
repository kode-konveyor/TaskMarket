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
import com.kodekonveyor.authentication.AuthenticatedUserServiceStubs;
import com.kodekonveyor.exception.ThrowableTester;
import com.kodekonveyor.market.register.MarketUserEntityRepositoryStubs;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("input validation")
@TestedService("CreateProjectController")
public class CreateProjectControllerInputValidationTest
    extends CreateProjectControllerTestBase {

  @Test
  @DisplayName("When project Id is just positive, we throw no exception")
  public void projectId1test() {
    AuthenticatedUserServiceStubs.manager(authenticatedUserService);
    MarketUserEntityRepositoryStubs
        .userBalanceMoreThanBudget(marketUserEntityRepository);
    ThrowableTester.assertNoException(
        () -> createProjectController.call(ProjectDTOTestData.getPositiveId())
    );
  }

  @Test
  @DisplayName("When project Id is non-positive, we throw an exception")
  public void projectIdtest() {
    AuthenticatedUserServiceStubs.manager(authenticatedUserService);
    ThrowableTester.assertThrows(
        () -> createProjectController.call(ProjectDTOTestData.getNonPositiveId())
    )

        .assertMessageIs(
            CreateProjectControllerTestData.PROJECT_ID_NON_POSITIVE_EXCEPTION
        );
  }

  @Test
  @DisplayName("When project Id is zero, we throw an exception")
  public void projectIdZerotest() {
    AuthenticatedUserServiceStubs.manager(authenticatedUserService);
    ThrowableTester.assertThrows(
        () -> createProjectController.call(ProjectDTOTestData.getZeroId())
    ).assertMessageIs(
        CreateProjectControllerTestData.PROJECT_ID_NON_POSITIVE_EXCEPTION
    );
  }

  @Test
  @DisplayName(
    "When project name is invalid (Expected Format - '^kode-konveyor/\\\\b[A-Za-z0-9]*$'), we throw an exception"
  )
  public void projectNameInvalidtest() {
    AuthenticatedUserServiceStubs.manager(authenticatedUserService);
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
    AuthenticatedUserServiceStubs.manager(authenticatedUserService);
    ThrowableTester.assertThrows(
        () -> createProjectController.call(ProjectDTOTestData.getNullName())
    )

        .assertMessageIs(
            CreateProjectControllerTestData.PROJECT_NAME_NULL_EXCEPTION
        );
  }

  @Test
  @DisplayName("When project parameters are valid no exception is thrown")
  public void validProjecttest() {
    AuthenticatedUserServiceStubs.manager(authenticatedUserService);
    MarketUserEntityRepositoryStubs
        .userBalanceMoreThanBudget(marketUserEntityRepository);
    ThrowableTester.assertNoException(
        () -> createProjectController.call(ProjectDTOTestData.get())
    );
  }
}
