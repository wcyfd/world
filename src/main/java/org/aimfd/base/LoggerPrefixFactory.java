package org.aimfd.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerPrefixFactory {
	public static LoggerPrefix getLogger(Class<?> clazz) {
		Logger logger = LoggerFactory.getLogger(clazz);
		LoggerPrefix loggerPrefix = new LoggerPrefix(logger);
		return loggerPrefix;
	}

	public static LoggerPrefix getLogger(String name) {
		Logger logger = LoggerFactory.getLogger(name);
		LoggerPrefix loggerPrefix = new LoggerPrefix(logger);
		return loggerPrefix;
	}
}
