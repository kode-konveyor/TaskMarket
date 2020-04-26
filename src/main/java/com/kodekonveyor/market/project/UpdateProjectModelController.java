package com.kodekonveyor.market.project;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodekonveyor.market.UrlMapConstants;

@RestController
public class UpdateProjectModelController {

  @Autowired
  ProjectEntityRepository projectEntityRepository;
  @Autowired
  MilestoneEntityRepository milestoneEntityRepository;

  @PutMapping(UrlMapConstants.UPDATE_PROJECT_MODEL_PATH)
  public void
      call(final ProjectModelDTO projectModelDTO, final String projectName) {
    final ProjectEntity project = projectEntityRepository
        .findByName(projectName).get();
    final Set<Long> milestoneIds = projectModelDTO.getMilestone();
    final Set<MilestoneEntity> milestones = new HashSet<>();
    for (final Long milestoneId : milestoneIds) {
      final MilestoneEntity milestoneEntity =
          milestoneEntityRepository.findById(milestoneId).get();
      milestones.add(milestoneEntity);
    }
    project.setMilestone(milestones);
    projectEntityRepository.save(project);

  }

}
