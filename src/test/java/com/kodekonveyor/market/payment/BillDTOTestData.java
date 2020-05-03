package com.kodekonveyor.market.payment;

import java.util.Set;

import javax.annotation.Generated;

import com.kodekonveyor.market.register.MarketUserTestData;

@Generated("by zenta-tools")
public class BillDTOTestData {

  public final static BillDTO get() {
    final BillDTO billDTO = new BillDTO();
    billDTO.setId(BillTestData.ID);
    billDTO.setBilledItem(Set.of(BilledItemTestData.ID));
    billDTO.setMarketUser(MarketUserTestData.ID);
    billDTO.setBillPicture(BillTestData.BILL_PICTURE);
    billDTO.setIsChecked(BillTestData.IS_CHECKED);
    billDTO.setBillAmountInCents(BillTestData.BILL_AMOUNT_IN_CENTS);

    return billDTO;
  };

}
