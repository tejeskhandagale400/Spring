package com.capgemini.mmbank.validation;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.capgemini.mmbank.account.SavingsAccount;
import com.capgemini.mmbank.exception.InvalidInputException;

@Component
@Aspect
public class MMBankappValidation {
	private Logger logger = Logger.getLogger(MMBankappValidation.class.getName());

	@Around("execution(* com.capgemini.mmbank.service.*.deposit(..))")
	public void logAroundDeposite(ProceedingJoinPoint joinPoint) {

		Object parametrs[] = joinPoint.getArgs();

		if ((Double) parametrs[1] > 0) {
			try {
				joinPoint.proceed();
			} catch (Throwable e) {
				logger.info(" Invalid account");
			}
		}

		else {
			logger.info("Invalid Input Amount!"); // throw new Exception();
			throw new InvalidInputException("Invalid Input Amount!");
		}

	}

	@Around("execution(* com.capgemini.mmbank.service.*.withdraw(..))")
	public void logAroundWithdraw(ProceedingJoinPoint joinPoint) {

		Object parametrs[] = joinPoint.getArgs();
		SavingsAccount account = (SavingsAccount) parametrs[0];

		if ((Double) parametrs[1] > 0 && account.getBankAccount().getAccountBalance() >= (Double) parametrs[1]) {
			try {
				joinPoint.proceed();
			} catch (Throwable e) {
				logger.info("Invalid Input or Insufficient Funds!");
			}
		}

		else {
			logger.info("Invalid Input or Insufficient Funds!"); // throw new Exception();
			throw new InvalidInputException("Invalid Input Amount!");
		}

	}

	@AfterThrowing(pointcut = "execution(* com.capgemini.mmbank.service.*.*(..))", throwing = "ex")
	public void doRecoveryActions(JoinPoint jp, Throwable ex) {
		logger.info("Exception is   " + ex );
	}

}
