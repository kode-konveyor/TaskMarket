package com.kodekonveyor.market.project;

import com.kodekonveyor.annotations.TestedBehaviour;
import com.kodekonveyor.annotations.TestedService;
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

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("compile output")
@TestedService("UpdateProjectModelController")
public class UpdateProjectModelControllerCompileOutput4Test
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
    @DisplayName("Id is returned successfully and is same as the value saved in DB.")
    public void test17() {
        assertEquals(projectDTO.getId(), captor.getValue().getId());
    }

    @Test
    @DisplayName("Project Id is returned successfully and is same as the value saved in DB.")
    public void test18() {
        assertEquals(
                projectDTO.getProjectId(), captor.getValue().getProjectId()
        );
    }

    @Test
    @DisplayName("Pull Request Ids are returned successfully and is same as the value saved in DB.")
    public void test19() {
        assertEquals(
                projectDTO.getPullRequest(),
                captor.getValue().getPullRequest().stream().map(PullRequestEntity::getId).collect(Collectors.toSet())
        );
    }

    @Test
    @DisplayName("URL is returned successfully and is same as the value saved in DB.")
    public void test20() {
        assertEquals(projectDTO.getUrl(), captor.getValue().getUrl());
    }

    @Test
    @DisplayName("Milestones are returned successfully and is same as the value saved in DB.")
    public void test21() {
        assertEquals(
                projectDTO.getMilestone(), captor.getValue().getMilestone().stream().map(MilestoneEntity::getId).collect(Collectors.toSet())
        );
    }
}
