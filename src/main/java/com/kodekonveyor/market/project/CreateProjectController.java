package com.kodekonveyor.market.project;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodekonveyor.market.MarketConstants;
import com.kodekonveyor.market.UrlMapConstants;
import com.kodekonveyor.market.ValidationException;

@RestController
public class CreateProjectController {

  @PostMapping(
      value = UrlMapConstants.PROJECT_PATH, consumes = "application/json"
  )
  public Object call(final ProjectDTO dto) {

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

    return null;
  }

}
