package com.kodekonveyor.market.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kodekonveyor.market.MarketConstants;
import com.kodekonveyor.market.UrlMapConstants;
import com.kodekonveyor.market.ValidationException;

@RestController
public class CreateProjectController {

  @Autowired
  ProjectEntityRepository projectEntityRepository;

  @PostMapping(
      value = UrlMapConstants.PROJECT_PATH, consumes = "application/json"
  )
  public ProjectDTO call(@RequestBody final ProjectDTO dto) {

    inputValidation(dto);
    storage(dto);

    return dto;
  }

  @PostMapping(
      value = UrlMapConstants.PROJECT_PATH,
      consumes = "application/x-www-form-urlencoded"
  )
  public ProjectDTO callForUrlencoded(final ProjectDTO projectDTO) {
    storage(projectDTO);

    return projectDTO;

  }

  private void inputValidation(final ProjectDTO dto) {
    if (null == dto.getName())
      throw new ValidationException(
          MarketConstants.PROJECT_NAME_NULL_EXCEPTION
      );

    if (!dto.getName().matches(MarketConstants.PROJECT_NAME_REGEX))
      throw new ValidationException(
          MarketConstants.PROJECT_NAME_INVALID_EXCEPTION
      );

    if (dto.getId() < MarketConstants.MINIMUM_PROJECT_ID)
      throw new ValidationException(
          MarketConstants.PROJECT_ID_NON_POSITIVE_EXCEPTION
      );
  }

  private void storage(final ProjectDTO dto) {

    final ProjectEntity entity = new ProjectEntity();
    entity.setId(dto.getId());
    entity.setName(dto.getName());

    projectEntityRepository.save(entity);

  }

}
