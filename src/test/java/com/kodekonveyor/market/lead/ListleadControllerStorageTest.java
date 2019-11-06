package com.kodekonveyor.market.lead;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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
@TestedService("ListleadController")
public class ListleadControllerStorageTest {

  @InjectMocks
  ListleadController listleadController;
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
  @DisplayName("The data is listed")
  void test() {
    Mockito.doReturn(leadTestData.LEAD_ENTITY_LIST).when(leadEntityRepository)
        .findAll();
    final List<LeadDTO> ret = listleadController.call();
    assertEquals(leadTestData.LEAD_LIST, ret);
  }
}
