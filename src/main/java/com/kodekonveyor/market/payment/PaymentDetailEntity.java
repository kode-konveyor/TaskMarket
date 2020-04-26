package com.kodekonveyor.market.payment;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Generated("by zenta-tools")
@Data
@Entity
public class PaymentDetailEntity {
	@Id
  	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String accountId;
	private String bankId;
	private String name;
	private Long percentage;
	private TransferTypeEntity transferType;

}
