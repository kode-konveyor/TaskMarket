package com.kodekonveyor.market.payment;

import java.util.List;

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

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("Logging")
@TestedService("ListTransferTypesController")
public class ListTransferTypesControllerLoggingTest
    extends ListTransferTypesControllerTestBase {

  @Test
  @DisplayName(
    "A Call to the controller is logged"
  )
  void test() {
    TransferTypeEntityRepositoryStubs.behaviour(transferTypeEntityRepository);
    listTransferTypesController.call();
    Mockito.verify(logger).info(
        LoggingMarkerConstants.PAYMENT,
        List.of(TransferTypeEntityTestData.get()).toString()
    );
  }

  @Test
  @DisplayName(
    "Succesful return of transer Type DTO is logged"
  )
  void test1() {
    TransferTypeEntityRepositoryStubs.behaviour(transferTypeEntityRepository);
    listTransferTypesController.call();
    Mockito.verify(logger).debug(
        LoggingMarkerConstants.PAYMENT,
        TransferTypeTestData.TRANSFER_TYPE_DTO_RETURNED +
            List.of(TransferTypeTestData.ID)

    );
  }
}
