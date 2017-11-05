package com.springbook.biz.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

@Service
@Aspect
public class AroundAdvice {
	/*@Pointcut("execution(* com.springbook.biz..*Impl.*(..))")
	public void allPointcut(){}*/
	
	@Around("PointcutCommon.allPointcut()")
	public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable{
		String method = pjp.getSignature().getName();
		
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		//System.out.println("[BEFORE]: ����Ͻ� �޼ҵ� ���� �� ó���� ����");
		
		Object returnObj = pjp.proceed();
		
		//System.out.println("[AFTER]: ����Ͻ� �޼ҵ� ���� �� ó���� ����");
		stopWatch.stop();
		System.out.println(method + "() ���࿡ �ɸ� �ð� : " + stopWatch.getTotalTimeMillis() + "(ms) ��");
		return returnObj;
	}
}
