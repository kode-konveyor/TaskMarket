package com.kodekonveyor.market.project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.kodekonveyor.annotations.TestedBehaviour;
import com.kodekonveyor.annotations.TestedService;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("update milestones")
@TestedService("UpdateProjectModelController")
public class UpdateProjectModelControllerUpdateMilestonesTest
    extends UpdateProjectModelControllerTestBase {

  @Test
  @DisplayName("The project with updated milestones is saved successfully")
  public void test() {
    ProjectEntityStubs.behaviour(projectEntityRepository);
    updateProjectModelController.call(ModelExcerptDTOTestData.get());
    Mockito.verify(projectEntityRepository).save(ProjectEntityTestData.get());

  }

  @Test
  @DisplayName("The milestones of the project is updated successfully")
  public void test1() {
    ProjectEntityStubs.behaviour(projectEntityRepository);
    updateProjectModelController.call(ModelExcerptDTOTestData.get());
    assertEquals(
        ModelExcerptDTOTestData.get().getMilestones(),
        ProjectEntityTestData.get().getMilestones()
    );

  }
}
