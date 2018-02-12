/**
 * Copyright (C) General Electric Company 2018 . All Rights Reserved.
 * 
 * @author 999951
 *
 */
package com.ip;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(value = 1)
public class LoggingAspect {
	private long starttime = System.currentTimeMillis();
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Before("com.ip.SystemArchitecture.methodLoggerAop()")
	public void logBefore(JoinPoint joinPoint) {
		starttime = System.currentTimeMillis();
		LOGGER.info("Methode Start : " + joinPoint.getSignature().getName());
	}

	@After("com.ip.SystemArchitecture.methodLoggerAop()")
	public void logAfter(JoinPoint joinPoint) {
		LOGGER.info("Methode End : " + joinPoint.getSignature().getName() + " took "
				+ (System.currentTimeMillis() - starttime) + "ms execution.");
	}
}