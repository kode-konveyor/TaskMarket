package com.kodekonveyor.market.tasks;

public enum TaskStatusEnum {

  DONE("done"),
  IN_PROGRESS("in progress"),
  OPEN("open"),
  UP_FOR_GRAB("up for grab"),
  NOT_IN_MODEL("not in model");

  public final String value;

  TaskStatusEnum(final String label) {
    value = label;
  }

  public String getValue() {
    return value;
  }
}
