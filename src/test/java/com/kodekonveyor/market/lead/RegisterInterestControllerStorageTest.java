package com.kodekonveyor.market.lead;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import com.kodekonveyor.market.LogSeverityEnum;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("storage")
@TestedService("RegisterInterestController")
public class RegisterInterestControllerStorageTest
    extends RegisterInterestControllerTestBase {

  @Test
  @DisplayName("The data is stored for application/json requests")
  void test() {
    registerInterestController.call(leadTestData.LEAD);
    verify(leadEntityRepository).save(leadTestData.LEAD_ENTITY_NO_ID);
  }

  @Test
  @DisplayName(
    "The data is stored when using application/x-www-form-urlencoded request"
  )
  void test1() {
    registerInterestController.callForUrlencoded(leadTestData.LEAD);
    verify(leadEntityRepository).save(leadTestData.LEAD_ENTITY_NO_ID);
  }

  @Test
  @DisplayName("The stored data is returned for application/json requests")
  void test2() {
    final LeadDTO ret = registerInterestController.call(leadTestData.LEAD);
    assertEquals(leadTestData.LEAD, ret);
  }

  @Test
  @DisplayName(
    "The stored data is returned for application/x-www-form-urlencoded requests"
  )
  void test21() {
    final LeadDTO ret =
        registerInterestController.callForUrlencoded(leadTestData.LEAD);
    assertEquals(leadTestData.LEAD, ret);
  }

  @Test
  @DisplayName("The call of the service is logged with the created entity")
  void test3() {
    registerInterestController.call(leadTestData.LEAD);
    verify(loggerService)
        .call(
            logTestData.LEAD_RECEIVED, LogSeverityEnum.INFO,
            leadTestData.LEAD.toString()
        );
  }

}
