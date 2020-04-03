package com.kodekonveyor.logging;

import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

public class LoggingMarkerConstants {

  public static final Marker AUTHENTICATION =
      MarkerFactory.getMarker("authentication");
  public static final Marker PROJECT =
      MarkerFactory.getMarker("project");
  public static final Marker GITHUB =
      MarkerFactory.getMarker("github");
  public static final Marker LEAD =
      MarkerFactory.getMarker("lead");
}