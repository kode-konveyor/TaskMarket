package com.kodekonveyor.market.project;

import com.kodekonveyor.annotations.TestedBehaviour;
import com.kodekonveyor.annotations.TestedService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("compile output")
@TestedService("UpdateProjectModelController")
public class UpdateProjectModelControllerCompileOutput3Test
    extends UpdateProjectModelControllerTestBase {

  @Test
  @DisplayName("When saving entity to db returns different data, the response is different for the input to api.")
  public void test12() {
    ProjectEntityRepositoryStubs.mockIncorrectSaveBehaviour(projectEntityRepository);

    ProjectDTO projectDTO = updateProjectModelController
            .call(ProjectModelDTOTestData.get(), ProjectTestData.PROJECT_NAME);

    assertNotEquals(projectDTO, ProjectDTOTestData.getUrl());
  }
}
