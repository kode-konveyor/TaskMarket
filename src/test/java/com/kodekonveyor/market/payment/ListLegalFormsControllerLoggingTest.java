package com.kodekonveyor.market.payment;

import static org.mockito.Mockito.verify;

import java.util.List;

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
import com.kodekonveyor.logging.LoggingMarkerConstants;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("Logging")
@TestedService("ListLegalFormsController")
public class ListLegalFormsControllerLoggingTest
    extends ListLegalFormsControllerTestBase {

  @Test
  @DisplayName(
    "A Call to the service is logged when it returns the list of all legal forms"
  )
  void testFullList() {
    LegalFormEntityRepositoryStubs.behaviour(legalFormEntityRepository);
    listLegalFormsController.call();
    verify(logger)
        .info(
            LoggingMarkerConstants.PAYMENT, LegalFormTestData.LEGAL_FORMS,
            List.of(LegalFormDTOTestData.get()).toString()
        );
  }
}
