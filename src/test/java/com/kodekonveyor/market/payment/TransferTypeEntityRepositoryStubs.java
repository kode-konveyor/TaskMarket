package com.kodekonveyor.market.payment;

import static org.mockito.Mockito.doReturn;

import java.util.List;
import java.util.Optional;

public class TransferTypeEntityRepositoryStubs {

  public static void
      behaviour(final TransferTypeEntityRepository transferTypeEntityRepository) {
    doReturn(Optional.of(TransferTypeEntityTestData.get()))
        .when(transferTypeEntityRepository).findById(TransferTypeTestData.ID);
    doReturn(List.of(TransferTypeEntityTestData.get()))
        .when(transferTypeEntityRepository)
        .findAll();
  }

  public static void
      behaviourEmpty(
          final TransferTypeEntityRepository transferTypeEntityRepository
      ) {
    doReturn(List.of())
        .when(transferTypeEntityRepository)
        .findAll();

  }
}
