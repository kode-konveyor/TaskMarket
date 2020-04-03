package com.kodekonveyor.market.tasks;

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
import com.kodekonveyor.authentication.AuthenticatedUserStubs;
import com.kodekonveyor.logging.LoggingMarkerConstants;
import com.kodekonveyor.market.UrlMapConstants;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("Logging")
@TestedService("UpdateTasksController")
public class UpdateTasksControllerLoggingTest
    extends UpdateTasksControllerTestBase {

  @Test
  @DisplayName("The call of the service is logged")
  void test() {
    AuthenticatedUserStubs.kodekonveyorContract(authenticatedUserService);
    updateTasksController.call();
    loggerService
        .info(LoggingMarkerConstants.TASK, UrlMapConstants.TASK_UPDATE_PATH);
  }
}
