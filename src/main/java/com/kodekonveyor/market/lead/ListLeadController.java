package com.kodekonveyor.market.lead;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodekonveyor.authentication.AuthenticatedUserService;
import com.kodekonveyor.authentication.RoleEntity;
import com.kodekonveyor.authentication.UserEntity;
import com.kodekonveyor.market.LoggerService;
import com.kodekonveyor.market.UnauthorizedException;

@RestController
public class ListLeadController {

  @Autowired
  LeadEntityRepository leadEntityRepository;

  @Autowired
  LoggerService loggerService;

  @Autowired
  AuthenticatedUserService authenticatedUserService;

  @GetMapping("/member/lead")
  public List<LeadDTO> call() {
    loggerService.call("member/lead");
    final UserEntity user = authenticatedUserService.call();
    if (!hasRole(user, "kodekonveyor_sales"))
      throw new UnauthorizedException("Unauthorized");
    final Iterable<LeadEntity> leads = leadEntityRepository.findAll();
    final List<LeadDTO> ret = new ArrayList<>();
    for (final LeadEntity lead : leads) {
      final LeadDTO leadDTO = new LeadDTO();//NOPMD
      leadDTO.setEmail(lead.getEmail());
      leadDTO.setFirstName(lead.getFirstName());
      leadDTO.setInterest(lead.getInterest());
      ret.add(leadDTO);
    }
    return ret;
  }

  private boolean hasRole(final UserEntity user, final String roleName) {
    for (final RoleEntity role : user.getRoles())
      if (role.getName().equals(roleName))
        return true;
    return false;
  }
}
