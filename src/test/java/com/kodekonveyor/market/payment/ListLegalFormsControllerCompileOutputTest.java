package com.kodekonveyor.market.payment;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("compile output")
@TestedService("ListLegalFormsController")
class ListLegalFormsControllerCompileOutputTest
    extends ListLegalFormsControllerTestBase {

  @Test
  @DisplayName("Returns empty list when no legal form exists")
  void testEmptyList() {
    LegalFormEntityRepositoryStubs.behaviourEmpty(legalFormEntityRepository);
    assertEquals(List.of(), listLegalFormsController.call());
  }

  @Test
  @DisplayName("Returns the list of all legal forms")
  void testFullList() {
    LegalFormEntityRepositoryStubs.behaviour(legalFormEntityRepository);
    assertEquals(
        List.of(LegalFormDTOTestData.get()), listLegalFormsController.call()
    );
  }

}
