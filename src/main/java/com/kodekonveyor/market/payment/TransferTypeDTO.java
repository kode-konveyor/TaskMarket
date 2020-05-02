
package com.kodekonveyor.market.payment;

import javax.annotation.Generated;

import lombok.Data;

@Generated("by zenta-tools")
@Data
public class TransferTypeDTO {
	private Long id;
	private String transferTypeName;
	private String accountIdLabel;
	private Boolean isBankIdShown;
	private String bankIdLabel;
	
}
