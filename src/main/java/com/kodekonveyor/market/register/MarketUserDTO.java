package com.kodekonveyor.market.register;

import java.util.Set;

import javax.annotation.Generated;

import lombok.Data;

@Generated("by zenta-tools")
@Data
public class MarketUserDTO {
	private Long id;
	private Long balanceInCents;
	private String email;
	private Boolean isTermsAccepted;
	private String legalAddress;
	private String legalName;
	private String personalName;
	private Long legalForm;
	private Long user;
	private Set<Long> paymentDetail;
	
}
