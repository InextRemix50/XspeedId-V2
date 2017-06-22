package com.voyages.sncf.logger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

@Component
public class XspeedItLogger implements Log {

	Log	logger = LogFactory.getLog(XspeedItLogger.class.getName());
	
	@Override
	public void debug(Object arg0) {
		logger.debug(arg0);
	}

	@Override
	public void debug(Object arg0, Throwable arg1) {
		logger.debug(arg0, arg1);
	}

	@Override
	public void error(Object arg0) {
		logger.error(arg0);
	}

	@Override
	public void error(Object arg0, Throwable arg1) {
		logger.debug(arg0, arg1);
	}

	@Override
	public void fatal(Object arg0) {
		logger.fatal(arg0);
	}

	@Override
	public void fatal(Object arg0, Throwable arg1) {
		logger.fatal(arg0, arg1);
	}

	@Override
	public void info(Object arg0) {
		logger.info(arg0);
	}

	@Override
	public void info(Object arg0, Throwable arg1) {
		logger.info(arg0, arg1);
	}

	@Override
	public boolean isDebugEnabled() {
		return false;
	}

	@Override
	public boolean isErrorEnabled() {
		return false;
	}

	@Override
	public boolean isFatalEnabled() {
		return false;
	}

	@Override
	public boolean isInfoEnabled() {
		return false;
	}

	@Override
	public boolean isTraceEnabled() {
		return false;
	}

	@Override
	public boolean isWarnEnabled() {
		return false;
	}

	@Override
	public void trace(Object arg0) {
		logger.trace(arg0);
	}

	@Override
	public void trace(Object arg0, Throwable arg1) {
		logger.trace(arg0, arg1);
	}

	@Override
	public void warn(Object arg0) {
		logger.warn(arg0);
	}

	@Override
	public void warn(Object arg0, Throwable arg1) {
		logger.warn(arg0, arg1);
	}

}
