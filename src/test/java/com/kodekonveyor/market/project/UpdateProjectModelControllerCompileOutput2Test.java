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
public class UpdateProjectModelControllerCompileOutput2Test
    extends UpdateProjectModelControllerTestBase {

  private ProjectDTO projectDTO;

  @BeforeEach
  public void testSetUp() {
    projectDTO = updateProjectModelController
        .call(ProjectModelDTOTestData.get(), ProjectTestData.PROJECT_NAME);
  }

  @Test
  @DisplayName("The projectDTO role returned successfully")
  public void test7() {
    assertEquals(projectDTO.getRole(), ProjectDTOTestData.getUrl().getRole());
  }

  @Test
  @DisplayName("The projectDTO name returned successfully")
  public void test8() {
    assertEquals(projectDTO.getName(), ProjectDTOTestData.getUrl().getName());
  }

  @Test
  @DisplayName("The projectDTO description returned successfully")
  public void test9() {
    assertEquals(
        projectDTO.getDescription(),
        ProjectDTOTestData.getUrl().getDescription()
    );
  }

  @Test
  @DisplayName("The projectDTO isPublic returned successfully")
  public void test10() {
    assertEquals(
        projectDTO.getIsPublic(), ProjectDTOTestData.getUrl().getIsPublic()
    );
  }

  @Test
  @DisplayName("The projectDTO budget returned successfully")
  public void test11() {
    assertEquals(
        projectDTO.getBudgetInCents(),
        ProjectDTOTestData.getUrl().getBudgetInCents()
    );
  }
}
