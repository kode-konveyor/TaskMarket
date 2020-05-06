package com.kodekonveyor.market.lead;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodekonveyor.authentication.AuthenticatedUserService;
import com.kodekonveyor.authentication.UserEntity;
import com.kodekonveyor.logging.LoggingMarkerConstants;
import com.kodekonveyor.market.MarketConstants;
import com.kodekonveyor.market.UnauthorizedException;
import com.kodekonveyor.market.UrlMapConstants;
import com.kodekonveyor.market.project.ProjectEntity;
import com.kodekonveyor.market.project.ProjectEntityRepository;

@RestController
public class ListLeadController {

  @Autowired
  AuthenticatedUserService authenticatedUserService;

  @Autowired
  LeadEntityRepository leadEntityRepository;

  @Autowired
  Logger loggerService;

  @Autowired
  ProjectEntityRepository projectEntityRepository;

  @GetMapping(UrlMapConstants.LIST_LEAD_PATH)
  public Set<LeadDTO> call() {
    loggerService
        .info(LoggingMarkerConstants.LEAD, LeadConstants.LISTING_LEADS);
    final UserEntity user = authenticatedUserService.call();
    final ProjectEntity project =
        projectEntityRepository
            .findByName(MarketConstants.KODE_KONVEYOR_PROJECT_NAME).get();
    if (
      !CheckRoleUtil
          .hasRole(user, project, MarketConstants.KODEKONVEYOR_SALES_ROLE)
    )
      throw new UnauthorizedException(LeadConstants.UNAUTHORIZED);
    final Iterable<LeadEntity> leads = leadEntityRepository.findAll();
    final Set<LeadDTO> ret = new HashSet<>();
    for (final LeadEntity lead : leads) {
      final LeadDTO leadDTO = createLeadTDO();
      leadDTO.setId(lead.getId());
      leadDTO.setEmail(lead.getEmail());
      leadDTO.setFirstName(lead.getFirstName());
      leadDTO.setInterest(lead.getInterest());
      ret.add(leadDTO);
    }
    return ret;
  }

  private LeadDTO createLeadTDO() {
    return new LeadDTO();
  }

}
