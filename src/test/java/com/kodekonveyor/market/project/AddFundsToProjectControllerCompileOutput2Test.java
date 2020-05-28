package com.kodekonveyor.market.project;

import static org.junit.Assert.assertEquals;

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
import com.kodekonveyor.market.register.MarketUserTestData;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("compile output")
@TestedService("AddFundsToProjectController")
public class AddFundsToProjectControllerCompileOutput2Test
    extends AddFundsToProjectControllerTestBase {

  private ProjectDTO projectDTO;

  @BeforeEach
  public void setUpTest() {
    projectDTO = addFundsToProjectController
        .call(ProjectTestData.ID_ADD_FUNDS, MarketUserTestData.BALANCE_IN_CENTS);
  }

  @Test
  @DisplayName("The project details with role returned successfully")
  public void test7() {
    assertEquals(projectDTO.getRole(), ProjectDTOTestData.getUrl().getRole());
  }

  @Test
  @DisplayName("The project details with name returned successfully")
  public void test8() {
    assertEquals(projectDTO.getName(), ProjectDTOTestData.getUrl().getName());
  }

  @Test
  @DisplayName("The project details with description returned successfully")
  public void test9() {
    assertEquals(
        projectDTO.getDescription(),
        ProjectDTOTestData.getUrl().getDescription()
    );
  }

  @Test
  @DisplayName("The project details with isPublic returned successfully")
  public void test10() {
    assertEquals(
        projectDTO.getIsPublic(), ProjectDTOTestData.getUrl().getIsPublic()
    );
  }

}
