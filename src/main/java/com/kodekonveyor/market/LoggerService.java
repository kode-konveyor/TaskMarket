package com.kodekonveyor.market;

import org.springframework.stereotype.Service;

import com.kodekonveyor.annotations.ExcludeFromCodeCoverage;

@Service
@ExcludeFromCodeCoverage("interface to underlaying framework")
public class LoggerService {

  public void
      call(
          final String category,
          final LogSeverityEnum severity,
          final String message
      ) {
    LogMarker marker;
    if (MarketConstants.markers.containsKey(category))
      marker = MarketConstants.markers.get(category);
    else {
      marker = new LogMarker(category);
      MarketConstants.markers.put(category, marker);
    }
    doLog(severity, message, marker);
  }

  private void doLog(
      final LogSeverityEnum severity, final String message,
      final LogMarker marker
  ) {
    switch (severity) {//NOPMD no default is needed
      case DEBUG:
        if (MarketConstants.logger.isDebugEnabled(marker))
          MarketConstants.logger.debug(marker, message);
        break;
      case INFO:
        if (MarketConstants.logger.isInfoEnabled(marker))
          MarketConstants.logger.info(marker, message);
        break;
      case WARNING:
        if (MarketConstants.logger.isWarnEnabled(marker))
          MarketConstants.logger.warn(marker, message);
        break;
      case ERROR:
        if (MarketConstants.logger.isErrorEnabled(marker))
          MarketConstants.logger.error(marker, message);
        break;
    }
  }

}
