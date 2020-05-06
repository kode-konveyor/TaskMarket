package com.kodekonveyor.market.payment;

import static org.mockito.Mockito.doReturn;

import java.util.Optional;

public class TransferTypeEntityRepositoryStubs {

  public static void
      behaviour(final TransferTypeEntityRepository transferTypeEntityRepository) {
    doReturn(Optional.of(TransferTypeEntityTestData.get()))
        .when(transferTypeEntityRepository).findById(TransferTypeTestData.ID);
  }

}
