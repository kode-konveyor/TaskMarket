package com.kodekonveyor.market.register;

import java.util.Set;

import javax.annotation.Generated;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.kodekonveyor.authentication.UserEntity;
import com.kodekonveyor.market.payment.BillEntity;
import com.kodekonveyor.market.payment.LegalFormEntity;
import com.kodekonveyor.market.payment.PaymentDetailEntity;
import com.kodekonveyor.market.project.ProjectEntity;
import com.kodekonveyor.market.project.PullrequestEntity;

import lombok.Data;

@Generated("by zenta-tools")
@Data
@Entity
public class MarketUserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long balanceInCents;
  private String email;
  private Boolean isTermsAccepted;
  private String legalAddress;
  private String legalName;
  private String personalName;
  @OneToOne
  private LegalFormEntity legalForm;
  @OneToOne
  private UserEntity user;
  @ElementCollection
  private Set<BillEntity> bill;
  @ElementCollection
  private Set<PaymentDetailEntity> paymentDetail;
  @ElementCollection
  private Set<ProjectEntity> project;
  @ElementCollection
  private Set<PullrequestEntity> pullRequest;

}
