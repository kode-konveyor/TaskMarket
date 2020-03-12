package com.kodekonveyor.market.register;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
class UserLegalInfoEntity {

  private boolean contractTermsAccepted;

  private String country;

  private String email;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String legalAddress;

  private String legalName;

  private String paymentDetails;

  private String paymentRegime;

}
