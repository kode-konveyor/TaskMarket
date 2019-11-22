package com.kodekonveyor.market.lead;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodekonveyor.authentication.AuthenticatedUserService;
import com.kodekonveyor.authentication.RoleEntity;
import com.kodekonveyor.authentication.UserEntity;
import com.kodekonveyor.market.LogSeverityEnum;
import com.kodekonveyor.market.LoggerService;
import com.kodekonveyor.market.MarketConstants;
import com.kodekonveyor.market.UnauthorizedException;
import com.kodekonveyor.market.UrlMapConstants;

@RestController
public class ListLeadController {

  @Autowired
  LeadEntityRepository leadEntityRepository;

  @Autowired
  LoggerService loggerService;

  @Autowired
  AuthenticatedUserService authenticatedUserService;

  @GetMapping(UrlMapConstants.LIST_LEAD_PATH)
  public List<LeadDTO> call() {
    loggerService
        .call("call", LogSeverityEnum.DEBUG, UrlMapConstants.LIST_LEAD_PATH);
    final UserEntity user = authenticatedUserService.call();
    if (!hasRole(user, MarketConstants.KODEKONVEYOR_SALES_ROLE))
      throw new UnauthorizedException("Unauthorized");
    final Iterable<LeadEntity> leads = leadEntityRepository.findAll();
    final List<LeadDTO> ret = new ArrayList<>();
    for (final LeadEntity lead : leads) {
      final LeadDTO leadDTO = createLeadTDO();
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

  private boolean hasRole(final UserEntity user, final String roleName) {
    return user.getRoles().stream()
        .anyMatch(role -> rolesNameEqual(role, roleName));
  }

  private boolean rolesNameEqual(final RoleEntity role, final String roleName) {
    return role.getName().equals(roleName);
  }
}
