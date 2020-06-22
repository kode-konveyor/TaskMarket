package com.kodekonveyor.market.payment;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Collectors;

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
@TestedService("ListTransferTypesController")
public class ListTransferTypesControllerCompileOutputTest
    extends ListTransferTypesControllerTestBase {

  @Test
  @DisplayName("Returns empty list when no transfer type exists")
  void test1() {
    TransferTypeEntityRepositoryStubs
        .behaviourEmpty(transferTypeEntityRepository);
    assertEquals(List.of(), listTransferTypesController.call());
  }

  @Test
  @DisplayName("Returns list of transfer type")
  void test2() {
    TransferTypeEntityRepositoryStubs.behaviour(transferTypeEntityRepository);
    assertEquals(
        List.of(TransferTypeDTOTestData.get()), listTransferTypesController.call()
    );
  }

  @Test
  @DisplayName("Returns list of transfer type with proper id")
  void test3() {
    TransferTypeEntityRepositoryStubs.behaviour(transferTypeEntityRepository);
    assertEquals(
        List.of(TransferTypeDTOTestData.get().getId()), listTransferTypesController.call().stream().map(TransferTypeDTO::getId).collect(Collectors.toList())
    );
  }

  @Test
  @DisplayName("Returns list of transfer type with proper Account label")
  void test4() {
    TransferTypeEntityRepositoryStubs.behaviour(transferTypeEntityRepository);
    assertEquals(
        List.of(TransferTypeDTOTestData.get().getAccountIdLabel()), listTransferTypesController.call().stream().map(TransferTypeDTO::getAccountIdLabel).collect(Collectors.toList())
    );
  }

  @Test
  @DisplayName("Returns list of transfer type with proper bank id label")
  void test5() {
    TransferTypeEntityRepositoryStubs.behaviour(transferTypeEntityRepository);
    assertEquals(
        List.of(TransferTypeDTOTestData.get().getBankIdLabel()), listTransferTypesController.call().stream().map(TransferTypeDTO::getBankIdLabel).collect(Collectors.toList())
    );
  }

  @Test
  @DisplayName("Returns list of transfer type with proper Bank id shown field")
  void test6() {
    TransferTypeEntityRepositoryStubs.behaviour(transferTypeEntityRepository);
    assertEquals(
        List.of(TransferTypeDTOTestData.get().getIsBankIdShown()), listTransferTypesController.call().stream().map(TransferTypeDTO::getIsBankIdShown).collect(Collectors.toList())
    );
  }

  @Test
  @DisplayName(
    "Returns list of transfer type with proper transfer type name field"
  )
  void test7() {
    TransferTypeEntityRepositoryStubs.behaviour(transferTypeEntityRepository);
    assertEquals(
        List.of(TransferTypeDTOTestData.get().getTransferTypeName()), listTransferTypesController.call().stream().map(TransferTypeDTO::getTransferTypeName).collect(Collectors.toList())
    );
  }

}
