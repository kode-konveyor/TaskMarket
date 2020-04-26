
package com.kodekonveyor.market.payment;

import javax.annotation.Generated;

import lombok.Data;

@Generated("by zenta-tools")
@Data
public class PaymentDetailDTO {
	private Long id;
	private String accountId;
	private String bankId;
	private String name;
	private Long percentage;
	private Long transferType;
	
}
