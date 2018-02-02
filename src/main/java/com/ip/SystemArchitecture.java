package com.ip;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SystemArchitecture {
	// Point cut for all productionscheduling api
	@Pointcut("execution(* com.ip.itest..*.*(..))")
	public void methodLoggerAop() {
	}

	// Point cut for all api
	@Pointcut("execution(* com.ip.itest..*.*(..))")
	public void isControllerLayer() {
	}

	// Point cut for service layer api
	@Pointcut("execution(* com.ip.itest..*.*(..))")
	public void inServiceLayer() {
	}
}