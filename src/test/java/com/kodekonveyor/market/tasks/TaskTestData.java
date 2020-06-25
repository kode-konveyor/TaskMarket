package com.kodekonveyor.market.tasks;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import javax.annotation.Generated;

@Generated("by zenta-tools")
public class TaskTestData {

  public final static Long ID = 1197L;

  public final static String SERVICE = "UpdateTasksController";

  public final static String BEHAVIOUR = "Get tasks from github";

  public final static String DESCRIPTION = "description comes here";

  public final static Long GITHUB_ID = 66l;

  public static final Long ID_1198 = 1198L;

  public static final Long GITHUB_ID_67 = 67L;

  public static final Instant DATE_OLDER_THAN_THREE_DAYS =
      Instant.now().minus(5, ChronoUnit.DAYS);

  public static final Instant DATE_THREE_DAYS_SINCE_GRABBED =
      Instant.now().minus(3, ChronoUnit.DAYS);

  public static final Instant DATE_FOUR_DAYS_SINCE_GRABBED =
      Instant.now().minus(4, ChronoUnit.DAYS);
 public static final Long ID_IN_PROGRESS = 1199L;

  public static final Long ID_2 = 1201L;

  public static final String TASK_NOT_UP_FOR_GRAB =
      "Task is not in the up for grab status";

  public static final String EXP_USER_NOT_ELIGIBLE_TO_GRAB =
        "User not eligible to grab task.";
}
