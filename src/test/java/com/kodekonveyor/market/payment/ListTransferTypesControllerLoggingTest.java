package com.kodekonveyor.market.payment;

import com.kodekonveyor.annotations.TestedBehaviour;
import com.kodekonveyor.annotations.TestedService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static com.kodekonveyor.logging.LoggingMarkerConstants.PAYMENT;
import static com.kodekonveyor.market.payment.TransferTypeTestData.EXP_LOG_LIST_TRANSFER_TYPES_CALL;
import static com.kodekonveyor.market.payment.TransferTypeTestData.EXP_LOG_LIST_TRANSFER_TYPES_SUCCESS_CALL;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("Logging")
@TestedService("ListTransferTypesController")
public class ListTransferTypesControllerLoggingTest
    extends ListTransferTypesControllerTestBase {

  @BeforeEach
  void setUp() {
    TransferTypeEntityRepositoryStubs
            .behaviourEmpty(transferTypeEntityRepository);
    listTransferTypesController.call();
  }

  @Test
  @DisplayName("The start of api execution for ListTransferTypesController is logged.")
  void test1() {
    Mockito.verify(logger)
            .info(PAYMENT, EXP_LOG_LIST_TRANSFER_TYPES_CALL);
  }

  @Test
  @DisplayName("The end of api execution for ListTransferTypesController is logged.")
  void test2() {
    Mockito.verify(logger)
            .debug(PAYMENT, EXP_LOG_LIST_TRANSFER_TYPES_SUCCESS_CALL);
  }

}
