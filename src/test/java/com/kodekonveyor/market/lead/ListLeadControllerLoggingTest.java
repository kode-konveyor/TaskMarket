package com.kodekonveyor.market.lead;

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
import com.kodekonveyor.authentication.AuthenticatedUserStubs;
import com.kodekonveyor.market.LogSeverityEnum;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("Logging")
@TestedService("ListLeadController")
public class ListLeadControllerLoggingTest extends ListLeadControllerTestBase {

  @Test
  @DisplayName("The call of the service is logged")
  void test2() {
    AuthenticatedUserStubs
        .salesUser(authenticatedUserService);

    listleadController.call();
    verify(loggerService)
        .call(
            ListLeadControllerTestData.CALL, LogSeverityEnum.DEBUG,
            ListLeadControllerTestData.LIST_LEAD_LOG
        );
  }

}
