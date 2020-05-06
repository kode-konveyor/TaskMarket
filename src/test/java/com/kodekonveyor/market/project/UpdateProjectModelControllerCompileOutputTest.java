package com.kodekonveyor.market.project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.kodekonveyor.annotations.TestedBehaviour;
import com.kodekonveyor.annotations.TestedService;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("compile output")
@TestedService("UpdateProjectModelController")
public class UpdateProjectModelControllerCompileOutputTest
    extends UpdateProjectModelControllerTestBase {

  private ProjectDTO projectDTO;

  @BeforeEach
  public void testSetUp() {
    projectDTO = updateProjectModelController
        .call(ProjectModelDTOTestData.get(), ProjectTestData.PROJECT_NAME);
  }

  @Test
  @DisplayName("The projectDTO returned successfully")
  public void test1() {
    assertEquals(projectDTO, ProjectDTOTestData.getUrl());
  }

  @Test
  @DisplayName("The projectDTO id returned successfully")
  public void test2() {
    assertEquals(projectDTO.getId(), ProjectDTOTestData.getUrl().getId());
  }

  @Test
  @DisplayName("The projectDTO projectId returned successfully")
  public void test3() {
    assertEquals(
        projectDTO.getProjectId(), ProjectDTOTestData.getUrl().getProjectId()
    );
  }

  @Test
  @DisplayName("The projectDTO pull request returned successfully")
  public void test4() {
    assertEquals(
        projectDTO.getPullRequest(),
        ProjectDTOTestData.getUrl().getPullRequest()
    );
  }

  @Test
  @DisplayName("The projectDTO url returned successfully")
  public void test5() {
    assertEquals(projectDTO.getUrl(), ProjectDTOTestData.getUrl().getUrl());
  }

  @Test
  @DisplayName("The projectDTO milestone returned successfully")
  public void test6() {
    assertEquals(
        projectDTO.getMilestone(), ProjectDTOTestData.getUrl().getMilestone()
    );
  }
}
