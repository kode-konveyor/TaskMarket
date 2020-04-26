package com.kodekonveyor.market.payment;

import java.util.Set;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Generated("by zenta-tools")
@Data
@Entity
public class BillEntity {
	@Id
  	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long billAmountInCents;
	private Object billPicture;
	private Boolean isChecked;
	private Set<BilledItemEntity> billedItem;

}
