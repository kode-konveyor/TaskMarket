package com.kodekonveyor.market.payment;

import java.util.Set;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.kodekonveyor.market.register.MarketUserEntity;

import lombok.Data;

@Generated("by zenta-tools")
@Data
@Entity
public class BillEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne(fetch = FetchType.LAZY)
  private MarketUserEntity marketUser;

  private Long billAmountInCents;

  private String billPicture;

  private Boolean isChecked;

  @OneToMany(fetch = FetchType.LAZY)
  private Set<BilledItemEntity> billedItem;

}
