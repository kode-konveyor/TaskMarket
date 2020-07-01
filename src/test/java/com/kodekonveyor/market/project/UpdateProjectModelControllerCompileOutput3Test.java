package com.kodekonveyor.market.project;

import com.kodekonveyor.annotations.TestedBehaviour;
import com.kodekonveyor.annotations.TestedService;
import com.kodekonveyor.authentication.RoleEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static java.util.stream.Collectors.toSet;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("compile output")
@TestedService("UpdateProjectModelController")
public class UpdateProjectModelControllerCompileOutput3Test
        extends UpdateProjectModelControllerTestBase {

    private ProjectDTO projectDTO;
    @Captor
    private ArgumentCaptor<ProjectEntity> captor;

    @BeforeEach
    public void setUpTest() {
        ProjectEntityRepositoryStubs.mockSaveAndCapture(projectEntityRepository, captor);
        projectDTO = updateProjectModelController
                .call(ProjectModelDTOTestData.get(), ProjectTestData.PROJECT_NAME);
    }

    @Test
    @DisplayName("Role is returned successfully and is same as the value saved in DB.")
    public void test12() {
        assertEquals(projectDTO.getRole(), captor.getValue().getRole().stream().map(RoleEntity::getId).collect(toSet()));
    }

    @Test
    @DisplayName("Name is returned successfully and is same as the value saved in DB.")
    public void test13() {
        assertEquals(projectDTO.getName(), captor.getValue().getName());
    }

    @Test
    @DisplayName("Description is returned successfully and is same as the value saved in DB.")
    public void test14() {
        assertEquals(
                projectDTO.getDescription(),
                captor.getValue().getDescription()
        );
    }

    @Test
    @DisplayName("isPublic is returned successfully and is same as the value saved in DB.")
    public void test15() {
        assertEquals(
                projectDTO.getIsPublic(), captor.getValue().getIsPublic()
        );
    }

    @Test
    @DisplayName("budgetInCents is returned successfully and is same as the value saved in DB.")
    public void test16() {
        assertEquals(
                projectDTO.getBudgetInCents(),
                captor.getValue().getBudgetInCents()
        );
    }
}
