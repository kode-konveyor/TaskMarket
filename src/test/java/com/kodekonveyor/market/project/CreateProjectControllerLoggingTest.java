package com.kodekonveyor.market.project;

import static org.mockito.Mockito.verify;

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
import com.kodekonveyor.logging.LoggingMarkerConstants;
import com.kodekonveyor.market.register.MarketUserEntityRepositoryStubs;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("Logging")
@TestedService("CreateProjectController")
public class CreateProjectControllerLoggingTest
    extends CreateProjectControllerTestBase {

  @Test
  @DisplayName("The call of the service is logged with the created entity")
  void test3() {
    AuthenticatedUserServiceStubs.projectManager(authenticatedUserService);
    MarketUserEntityRepositoryStubs
        .userBalanceMoreThanBudget(marketUserEntityRepository);
    createProjectController.call(ProjectDTOTestData.get());
    verify(loggerService)
        .info(
            LoggingMarkerConstants.PROJECT,
            CreateProjectControllerTestData.PROJECT_RECEIVED +
                ProjectDTOTestData.get().toString()
        );
  }

}
