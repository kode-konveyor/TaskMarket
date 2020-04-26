package com.kodekonveyor.market.payment;

import static org.mockito.Mockito.doReturn;

import java.util.Optional;

public class LegalFormEntityRepositoryStubs {

  public static void
      behaviour(final LegalFormEntityRepository legalFormEntityRepository) {
    doReturn(Optional.of(LegalFormEntityTestData.get()))
        .when(legalFormEntityRepository)
        .findById(LegalFormTestData.ID);
    doReturn(Optional.empty())
        .when(legalFormEntityRepository)
        .findById(LegalFormTestData.ID_INVALID);
  }

}
