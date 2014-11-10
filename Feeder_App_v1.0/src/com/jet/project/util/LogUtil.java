package com.jet.project.util;

import java.util.logging.Logger;


public class LogUtil {
	private static final Logger logger =  Logger.getLogger(LogUtil.class.getName());

	public static final void log(String message){
		logger.info(message);
	}
	
}
