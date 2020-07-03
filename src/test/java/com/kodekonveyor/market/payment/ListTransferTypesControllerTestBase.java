package com.kodekonveyor.market.payment;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;

public class ListTransferTypesControllerTestBase {

  @InjectMocks
  protected ListTransferTypesController listTransferTypesController;

  @Mock
  protected TransferTypeEntityRepository transferTypeEntityRepository;

  @Mock
  protected Logger logger;
}
