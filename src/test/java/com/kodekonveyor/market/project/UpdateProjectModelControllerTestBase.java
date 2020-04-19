package com.kodekonveyor.market.project;

import org.mockito.InjectMocks;
import org.mockito.Mock;

public class UpdateProjectModelControllerTestBase {

  @InjectMocks
  UpdateProjectModelController updateProjectModelController;

  @Mock
  ProjectEntityRepository projectEntityRepository;

}
