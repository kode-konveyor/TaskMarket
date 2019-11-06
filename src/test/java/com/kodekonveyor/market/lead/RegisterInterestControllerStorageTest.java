package com.kodekonveyor.market.lead;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.kodekonveyor.annotations.TestedBehaviour;
import com.kodekonveyor.annotations.TestedService;
import com.kodekonveyor.authentication.UserTestData;
import com.kodekonveyor.market.LoggerService;
import com.kodekonveyor.market.login.RegisterTestData;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("storage")
@TestedService("RegisterInterestController")
public class RegisterInterestControllerStorageTest {

  @InjectMocks
  RegisterInterestController registerInterestController;
  @Mock
  LeadEntityRepository leadEntityRepository;

  @Mock
  LoggerService loggerService;

  private LeadTestData leadTestData;

  @BeforeEach
  void setUp() {
    final UserTestData userTestData = new UserTestData();
    final RegisterTestData registerTestData =
        new RegisterTestData(userTestData);
    leadTestData = new LeadTestData(registerTestData);
  }

  @Test
  @DisplayName("The data is stored")
  void test() {
    registerInterestController.call(leadTestData.LEAD);
    verify(leadEntityRepository).save(leadTestData.LEAD_ENTITY_NO_ID);
  }

  @Test
  @DisplayName("The stored data is returned")
  void test2() {
    final LeadDTO ret = registerInterestController.call(leadTestData.LEAD);
    assertEquals(leadTestData.LEAD, ret);
  }

  @Test
  @DisplayName("The call of the service is logged with the created entity")
  void test3() {
    registerInterestController.call(leadTestData.LEAD);
    verify(loggerService).call(leadTestData.REGISTER_LOG);
  }

}
