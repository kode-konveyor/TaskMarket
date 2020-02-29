package com.kodekonveyor.market.project;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

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
@TestedBehaviour("storage")
@TestedService("CreateProjectController")
public class CreateProjectControllerStorageTest
    extends CreateProjectControllerTestBase {

  @Test
  @DisplayName(
    "verify project entity is saved successfully for application/json requests"
  )
  public void saveEntitytest() {
    createProjectController.call(ProjectDTOTestData.get());
    verify(projectEntityRepository).save(ProjectEntityTestData.get());
  }

  @Test
  @DisplayName("check project id is saved successfully")
  public void test() {
    createProjectController.callForUrlencoded(ProjectDTOTestData.get());
    assertEquals(
        ProjectDTOTestData.ID, createProjectController
            .callForUrlencoded(ProjectDTOTestData.get()).getId()
    );
  }

  @Test
  @DisplayName("check project name is saved successfully")
  public void test1() {
    assertEquals(
        ProjectEntityTestData.get().getName(), ProjectDTOTestData.NAME
    );
  }

  @Test
  @DisplayName(
    "The project is stored when using application/x-www-form-urlencoded request"
  )
  void test2() {
    createProjectController.callForUrlencoded(ProjectDTOTestData.get());
    verify(projectEntityRepository).save(ProjectEntityTestData.get());
  }

  @Test
  @DisplayName(
    "The stored project is returned for application/x-www-form-urlencoded requests"
  )
  void test21() {
    final ProjectDTO ret =
        createProjectController.callForUrlencoded(ProjectDTOTestData.get());
    assertEquals(ProjectDTOTestData.get(), ret);
  }

  @Test
  @DisplayName("The stored project is returned for application/json requests")
  void test3() {
    final ProjectDTO ret =
        createProjectController.call(ProjectDTOTestData.get());
    assertEquals(

        ret, ProjectDTOTestData.get()
    );
  }
}
