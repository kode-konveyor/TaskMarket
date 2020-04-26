package com.kodekonveyor.market.payment;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.kodekonveyor.market.project.PullrequestEntity;

import lombok.Data;

@Generated("by zenta-tools")
@Data
@Entity
public class BilledItemEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @OneToOne
  private PullrequestEntity pullRequest;
  private Long deliverableCount;
  private Long itemPriceInCents;
  private Long deliverablePriceInCents;

}
