package com.kodekonveyor.market.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodekonveyor.market.UrlMapConstants;

@RestController
public class UpdateProjectModelController {

  @Autowired
  ProjectEntityRepository projectEntityRepository;

  @PutMapping(UrlMapConstants.UPDATE_PROJECT_MODEL_PATH)
  public void call(final ModelExcerptDTO modelExcerptDTO) {

    final ProjectEntity project = projectEntityRepository
        .findByName(modelExcerptDTO.getTasks().getProject()).get(0);
    project.setMilestones(modelExcerptDTO.getMilestones());
    projectEntityRepository.save(project);

  }

}
