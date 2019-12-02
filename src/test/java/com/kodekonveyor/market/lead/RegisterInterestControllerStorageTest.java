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
import com.kodekonveyor.market.RegisterInterestControllerTestData;

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
    registerInterestController.call(LeadDTOTestData.get());
    verify(leadEntityRepository).save(LeadEntityTestData.getIdUninitialized());
  }

  @Test
  @DisplayName(
    "The data is stored when using application/x-www-form-urlencoded request"
  )
  void test1() {
    registerInterestController.callForUrlencoded(LeadDTOTestData.get());
    verify(leadEntityRepository).save(LeadEntityTestData.getIdUninitialized());
  }

  @Test
  @DisplayName("The stored data is returned for application/json requests")
  void test2() {
    final LeadDTO ret = registerInterestController.call(LeadDTOTestData.get());
    assertEquals(LeadDTOTestData.get(), ret);
  }

  @Test
  @DisplayName(
    "The stored data is returned for application/x-www-form-urlencoded requests"
  )
  void test21() {
    final LeadDTO ret =
        registerInterestController.callForUrlencoded(LeadDTOTestData.get());
    assertEquals(LeadDTOTestData.get(), ret);
  }

  @Test
  @DisplayName("The call of the service is logged with the created entity")
  void test3() {
    registerInterestController.call(LeadDTOTestData.get());
    verify(loggerService)
        .call(
            RegisterInterestControllerTestData.LEAD_RECEIVED,
            LogSeverityEnum.INFO,
            LeadDTOTestData.get().toString()
        );
  }

}
