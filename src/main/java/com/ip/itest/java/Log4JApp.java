package com.ip.itest.java;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.apache.log4j.NDC;

public class Log4JApp {

	final static Logger logger = Logger.getLogger(Log4JApp.class);
	public static void main(String[] args) {
		NDC.push("logging first NDC");
	    MDC.put("SR", "logging first MDC");
	    MDC.put("SR", "logging second MDC");

		NDC.push("logging second NDC ");	    
		logger.info("Calling getmore");
		getmore();
		
		NDC.pop();
		NDC.pop();
		NDC.pop();
		logger.error(MDC.get("SR"));
		logger.info("?"+MDC.get("SR2"));
		NDC.remove();
	}

	private static void getmore() {
		NDC.push("logging  NDC in getmore");
	    MDC.put("SR", "logging getmore MDC");
	    MDC.put("SR2", "logging  MDC in getmore");
		logger.info("In getmore block");
	}
}
