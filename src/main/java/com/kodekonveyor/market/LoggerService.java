package com.kodekonveyor.market;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kodekonveyor.annotations.ExcludeFromCodeCoverage;

@Service
@ExcludeFromCodeCoverage("interface to underlaying framework")
public class LoggerService {

  private static final Logger logger =
      LoggerFactory.getLogger(LoggerService.class);

  private static final Map<String, LogMarker> markers =
      new ConcurrentHashMap<>();

  public void
      call(
          final String category,
          final LogSeverityEnum severity,
          final String message
      ) {
    LogMarker marker;
    if (markers.containsKey(category))
      marker = markers.get(category);
    else {
      marker = new LogMarker(category);
      markers.put(category, marker);
    }
    doLog(severity, message, marker);
  }

  private void doLog(
      final LogSeverityEnum severity, final String message,
      final LogMarker marker
  ) {
    switch (severity) {//NOPMD no default is needed
      case DEBUG:
        if (logger.isDebugEnabled(marker))
          logger.debug(marker, message);
        break;
      case INFO:
        if (logger.isInfoEnabled(marker))
          logger.info(marker, message);
        break;
      case WARNING:
        if (logger.isWarnEnabled(marker))
          logger.warn(marker, message);
        break;
      case ERROR:
        if (logger.isErrorEnabled(marker))
          logger.error(marker, message);
        break;
    }
  }

}
