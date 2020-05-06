package com.kodekonveyor.market.register;

import java.util.Set;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.kodekonveyor.authentication.UserEntity;
import com.kodekonveyor.market.payment.LegalFormEntity;
import com.kodekonveyor.market.payment.PaymentDetailEntity;

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

  @OneToOne(fetch = FetchType.LAZY)
  private LegalFormEntity legalForm;

  @OneToOne(fetch = FetchType.LAZY)
  private UserEntity user;

  @OneToMany(fetch = FetchType.LAZY)
  private Set<PaymentDetailEntity> paymentDetail;

}
