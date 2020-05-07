package com.kodekonveyor.market.payment;

import org.mockito.InjectMocks;
import org.mockito.Mock;

public class ListLegalFormsControllerTestBase {

  @InjectMocks
  protected ListLegalFormsController listLegalFormsController;

  @Mock
  protected LegalFormEntityRepository legalFormEntityRepository;
}
