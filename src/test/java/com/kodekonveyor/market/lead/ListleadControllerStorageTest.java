package com.kodekonveyor.market.lead;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
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

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("storage")
@TestedService("ListLeadController")
public class ListleadControllerStorageTest extends ListLeadControllerTestBase {

  @Override
  @BeforeEach
  void setUp() {
    super.setUp();
    AuthenticatedUserStubs
        .salesUser(authenticatedUserService, userTestData);

  }

  @Test
  @DisplayName("The data is listed")
  void test() {
    final List<LeadDTO> ret = listleadController.call();
    assertEquals(leadTestData.LEAD_LIST, ret);
  }

  @Test
  @DisplayName("The call of the service is logged")
  void test2() {
    listleadController.call();
    verify(loggerService).call(leadTestData.LIST_LEAD_LOG);
  }

}