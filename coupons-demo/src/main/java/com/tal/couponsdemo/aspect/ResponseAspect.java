package com.tal.couponsdemo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.tal.couponsdemo.entities.GeneralResponse;

@Component
@Aspect
public class ResponseAspect {
	private static final Logger logger = LoggerFactory.getLogger(ResponseAspect.class);
	
	@Around("execution(* com.tal.couponsdemo..controllers..*(..))")
	public Object response(ProceedingJoinPoint proceedingJoinPoint) 	throws Throwable{
		logger.info("flow has been started");
		GeneralResponse response = new GeneralResponse(); 
		try {
			response.setResult( proceedingJoinPoint.proceed());
			logger.info("flow is done");
			return response;
		}catch (Throwable ex) {
			logger.error(ex.getMessage());
			response.setError(ex.getMessage());
			return response;
		}	
		}
}
