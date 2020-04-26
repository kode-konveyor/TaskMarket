
package com.kodekonveyor.market.payment;

import javax.annotation.Generated;

import lombok.Data;

@Generated("by zenta-tools")
@Data
public class BilledItemDTO {
	private Long id;
	private Long pullRequest;
	private Long deliverableCount;
	private Long itemPriceInCents;
	private Long deliverablePriceInCents;
	
}
