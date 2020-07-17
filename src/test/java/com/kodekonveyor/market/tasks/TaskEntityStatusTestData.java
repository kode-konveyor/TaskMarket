package com.kodekonveyor.market.tasks;

import javax.annotation.Generated;

@Generated("by zenta-tools")
public class TaskEntityStatusTestData {

    public static TaskEntity getMarketUserStatusNull() {
        final TaskEntity taskEntity = TaskEntityTestData.get();
        taskEntity.setStatus(null);
        return taskEntity;
    }

}
