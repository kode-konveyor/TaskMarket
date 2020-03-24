package com.kodekonveyor.market.tasks;

public enum TaskStatusEnum {

  DONE("done"),
  IN_PROGRESS("in progress"),
  OPEN("open"),
  UP_FOR_GRAB("up for grab");

  private String status;

  TaskStatusEnum(final String status) {
    this.status = status;
  }

  public String getValue() {
    return status;
  }

}
