package com.kodekonveyor.market.kpi;

public enum EventTypeEnum {

  GRAB("grab");

  public final String value;

  EventTypeEnum(final String label) {
    value = label;
  }

  public String getValue() {
    return value;
  }

}
