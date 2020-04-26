package com.kodekonveyor.market.register;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodekonveyor.authentication.AuthenticatedUserService;
import com.kodekonveyor.authentication.UserEntity;
import com.kodekonveyor.market.UrlMapConstants;
import com.kodekonveyor.market.payment.BillEntity;
import com.kodekonveyor.market.payment.PaymentDetailEntity;
import com.kodekonveyor.market.project.ProjectEntity;
import com.kodekonveyor.market.project.PullrequestEntity;

@RestController
public class ShowUserController {

  @Autowired
  AuthenticatedUserService authenticatedUserService;

  @Autowired
  MarketUserEntityRepository marketUserEntityRepository;

  @GetMapping(UrlMapConstants.SHOW_USER_PATH)
  public MarketUserDTO call() {
    final UserEntity userEntity = authenticatedUserService.call();

    final Optional<MarketUserEntity> entityP =
        marketUserEntityRepository.findByUser(userEntity);
    MarketUserEntity entity;
    final MarketUserDTO marketUserDTO = new MarketUserDTO();
    marketUserDTO.setUser(userEntity.getId());
    if (entityP.isEmpty()) {
      entity = new MarketUserEntity();
      entity.setUser(userEntity);
      marketUserEntityRepository.save(entity);
    } else
      entity = entityP.get();
    copyEntityToDT(entity, marketUserDTO);

    return marketUserDTO;
  }

  private void copyEntityToDT(
      final MarketUserEntity entity, final MarketUserDTO marketUserDTO
  ) {
    marketUserDTO.setId(entity.getId());
    marketUserDTO.setPersonalName(entity.getPersonalName());
    marketUserDTO.setLegalName(entity.getLegalName());
    marketUserDTO.setBalanceInCents(entity.getBalanceInCents());
    marketUserDTO.setEmail(entity.getEmail());
    marketUserDTO.setIsTermsAccepted(entity.getIsTermsAccepted());
    marketUserDTO.setLegalAddress(entity.getLegalAddress());
    marketUserDTO.setUser(entity.getUser().getId());
    if (entity.getLegalForm() != null)
      marketUserDTO.setLegalForm(entity.getLegalForm().getId());
    if (entity.getBill() != null)
      marketUserDTO.setBill(
          entity.getBill().stream().map(BillEntity::getId).collect(Collectors.toSet())
      );
    if (entity.getProject() != null)
      marketUserDTO.setProject(
          entity.getProject().stream().map(ProjectEntity::getId).collect(Collectors.toSet())
      );
    if (entity.getPullRequest() != null)
      marketUserDTO
          .setPullRequest(
              entity.getPullRequest().stream().map(PullrequestEntity::getId).collect(Collectors.toSet())
          );
    if (entity.getPaymentDetail() != null)
      marketUserDTO
          .setPaymentDetail(
              entity.getPaymentDetail().stream().map(PaymentDetailEntity::getId).collect(Collectors.toSet())
          );
  }

}
