package com.kodekonveyor.logging;

import javax.annotation.Generated;

import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

@Generated("by zenta-tools")
public class LogMessageTestData {

  public final static Long ID = 1793L;

  public final static Object ARGUMENT = "log message argument";

  public final static Marker LOG_CATEGORY_AUTH_DENY =
      MarkerFactory.getMarker("auth.deny");

  public final static String NOT_LOGGED_IN = "not logged in";

}
