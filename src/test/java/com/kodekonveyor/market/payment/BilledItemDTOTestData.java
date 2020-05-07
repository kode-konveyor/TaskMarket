package com.kodekonveyor.market.payment;

import javax.annotation.Generated;

import com.kodekonveyor.market.project.PullRequestTestData;

@Generated("by zenta-tools")
public class BilledItemDTOTestData {

  public final static BilledItemDTO get() {
    final BilledItemDTO billedItemDTO = new BilledItemDTO();
    billedItemDTO.setId(BilledItemTestData.ID);
    billedItemDTO.setDeliverablePriceInCents(
        BilledItemTestData.DELIVERABLE_PRICE_IN_CENTS
    );
    billedItemDTO.setDeliverableCount(BilledItemTestData.DELIVERABLE_COUNT);
    billedItemDTO.setPullRequest(PullRequestTestData.ID);
    billedItemDTO.setItemPriceInCents(BilledItemTestData.ITEM_PRICE_IN_CENTS);

    return billedItemDTO;
  };

}
