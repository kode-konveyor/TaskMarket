package com.kodekonveyor.market.payment;

import java.util.Set;

import javax.annotation.Generated;

import lombok.Data;

@Generated("by zenta-tools")
@Data
public class BillDTO {
	private Long id;
	private Long marketUser;
	private Long billAmountInCents;
	private Object billPicture;
	private Boolean isChecked;
	private Set<Long> billedItem;
	
}
