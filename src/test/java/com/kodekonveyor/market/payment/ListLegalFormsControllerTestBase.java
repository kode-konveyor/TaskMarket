package com.kodekonveyor.market.payment;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;

public class ListLegalFormsControllerTestBase {

  @InjectMocks
  protected ListLegalFormsController listLegalFormsController;

  @Mock
  protected LegalFormEntityRepository legalFormEntityRepository;

  @Mock
  protected Logger logger;
}
