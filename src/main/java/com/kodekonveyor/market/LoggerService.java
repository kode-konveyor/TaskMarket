package com.kodekonveyor.market;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LoggerService {
	private static final Logger logger = LoggerFactory.getLogger(LoggerService.class);

	public void call(final String msg) {
		logger.info(msg);
	}

}
