package com.springbook.biz.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class BeforeAdvice {
	/*@Pointcut("execution(* com.springbook.biz..*Impl.*(..))")
	public void allPointcut(){}*/
	
	@Before("PointcutCommon.allPointcut()")
	public void beforeLog(JoinPoint jp) {
		String method = jp.getSignature().getName();
		Object[] args= jp.getArgs();
		
		System.out.println("[beforeAdvice] 비지니스 로직 수행 전 동작");
		System.out.println(method + "() 메소드 ARGS 정보 : " + args[0].toString());
	}
}
