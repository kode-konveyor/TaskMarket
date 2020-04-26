package com.kodekonveyor.market.payment;

import java.util.Set;

import javax.annotation.Generated;

@Generated("by zenta-tools")
public class BillEntityTestData {

  public final static BillEntity get() {
    final BillEntity billEntity = new BillEntity();
    billEntity.setId(BillTestData.ID);
    billEntity.setBilledItem(Set.of(BilledItemEntityTestData.get()));
    billEntity.setBillPicture(BillTestData.BILL_PICTURE);
    billEntity.setIsChecked(BillTestData.IS_CHECKED);
    billEntity.setBillAmountInCents(BillTestData.BILL_AMOUNT_IN_CENTS);

    return billEntity;
  };

}
