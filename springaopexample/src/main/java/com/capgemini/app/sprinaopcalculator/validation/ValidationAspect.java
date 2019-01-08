package com.capgemini.app.sprinaopcalculator.validation;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ValidationAspect {
	private Logger logger = Logger.getLogger(ValidationAspect.class.getName());

	/*
	 * @Before("execution(* com.capgemini.app.sprinaopcalculator.calculator.Calculator.*(..))"
	 * ) public void log1() { logger.info("Before - Logging the method"); }
	 * 
	 * @After("execution(* com.capgemini.app.sprinaopcalculator.calculator.Calculator.*(..))"
	 * ) public void log2() { logger.info("After - Logging the method"); }
	 */
	@Around("execution(* com.capgemini.app.sprinaopcalculator.calculator.Calculator.*(..))")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
		logger.info("Before - Logging the method");
		logger.info("Parameters are  ");
		Object o = 0;
		Object parametrs[] = joinPoint.getArgs();

		for (int i = 0; i < parametrs.length; i++) {
			logger.info("Value of at  index " + i + "is " + parametrs[i]);
		}

		if ((Integer) parametrs[0] != 0 && (Integer) parametrs[1] != 0) {
			o = joinPoint.proceed();

		} else {
			logger.info("Invalid input");

			throw new Exception();

		}
		logger.info("" + joinPoint.getSignature());
		logger.info("After - Logging the method");
		return o;
	}

	/*
	 * @AfterReturning(pointcut =
	 * "execution(* com.capgemini.app.sprinaopcalculator.calculator.Calculator.*(..))"
	 * , returning = "retValue") public void logReturn(Integer retValue) {
	 * logger.info("return value =" + retValue);
	 * 
	 * }
	 */
	@AfterThrowing(pointcut = "execution(* com.capgemini.app.sprinaopcalculator.calculator.Calculator.*(..))", throwing = "ex")
	public void doRecoveryActions(JoinPoint jp, Throwable ex) {
		System.out.println("tejas " + ex);
		logger.info("teeeeeeeeejjjjjjaaaaa");
	}

}
