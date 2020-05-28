package com.kodekonveyor.market.payment;

import org.mockito.InjectMocks;
import org.mockito.Mock;

public class ListTransferTypesControllerTestBase {

  @InjectMocks
  protected ListTransferTypesController listTransferTypesController;

  @Mock
  protected TransferTypeEntityRepository transferTypeEntityRepository;
}
