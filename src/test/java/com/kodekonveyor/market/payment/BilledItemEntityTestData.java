package com.kodekonveyor.market.payment;

import javax.annotation.Generated;

import com.kodekonveyor.market.project.PullrequestEntityTestData;

@Generated("by zenta-tools")
public class BilledItemEntityTestData {

  public final static BilledItemEntity get() {
    final BilledItemEntity billedItemEntity = new BilledItemEntity();
    billedItemEntity.setId(BilledItemTestData.ID);
    billedItemEntity.setDeliverablePriceInCents(
        BilledItemTestData.DELIVERABLE_PRICE_IN_CENTS
    );
    billedItemEntity.setDeliverableCount(BilledItemTestData.DELIVERABLE_COUNT);
    billedItemEntity.setPullRequest(PullrequestEntityTestData.get());
    billedItemEntity
        .setItemPriceInCents(BilledItemTestData.ITEM_PRICE_IN_CENTS);

    return billedItemEntity;
  };

}
