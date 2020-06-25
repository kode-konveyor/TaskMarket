package com.kodekonveyor.market.kpi;

import com.kodekonveyor.authentication.UserEntityTestData;
import com.kodekonveyor.market.tasks.DateUtilTestData;

public class EventEntityTestData {

  public static final EventEntity get() {
    final EventEntity eventEntity = new EventEntity();
    eventEntity.setId(EventTestData.ID);
    eventEntity.setEventType(EventTestData.GRAB);
    eventEntity.setDate(DateUtilTestData.DATE);
    eventEntity.setUser(UserEntityTestData.get());
    return eventEntity;
  }

  public static final EventEntity getIdZero() {
    final EventEntity eventEntity = get();
    eventEntity.setId(EventTestData.ID_0);
    return eventEntity;
  }
}
