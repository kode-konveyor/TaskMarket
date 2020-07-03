package com.kodekonveyor.market.project;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.kodekonveyor.annotations.TestedBehaviour;
import com.kodekonveyor.annotations.TestedService;
import com.kodekonveyor.logging.LoggingMarkerConstants;
import com.kodekonveyor.market.register.MarketUserTestData;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("Logging")
@TestedService("AddFundsToProjectController")
public class AddFundsToProjectControllerLoggingTest
    extends AddFundsToProjectControllerTestBase {

  @Test
  @DisplayName("The call of the controller is logged")
  void test() {
    addFundsToProjectController
        .call(ProjectTestData.ID_ADD_FUNDS, MarketUserTestData.BALANCE_IN_CENTS);
    Mockito.verify(logger).info(
        LoggingMarkerConstants.PROJECT, ProjectTestData.ID_ADD_FUNDS.toString()
    );
  }

  @Test
  @DisplayName("return of DTO is logged")
  void test2() {
    addFundsToProjectController
        .call(ProjectTestData.ID_ADD_FUNDS, MarketUserTestData.BALANCE_IN_CENTS);
    Mockito.verify(logger).debug(
        LoggingMarkerConstants.PROJECT,
        ProjectTestData.PROJECT_DTO_RETURNED_SUCCESSFULLY +
            ProjectTestData.ID_ADD_FUNDS.toString()
    );
  }
}
