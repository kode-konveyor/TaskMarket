package com.kodekonveyor.market.tasks;

public enum TaskStatusEnum {

  DONE("done"),
  IN_PROGRESS("in progress"),
  OPEN("open"),
  UP_FOR_GRAB("up for grab");

  private String status;

  TaskStatusEnum(final String status) {
    setStatus(status);
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(final String status) {
    this.status = status;
  }
}
