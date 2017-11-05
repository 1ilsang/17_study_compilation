package com.springbook.biz.common;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class AfterAdvice {
	
/*	@Pointcut("execution(* com.springbook.biz..*Impl.*(..))")
	public void allPointcut(){}
	*/
	@After("PointcutCommon.allPointcut()")
	public void finallyLog(){
		System.out.println("[After] �����Ͻ� ���� ���� �� ������ ���� (finally)");
	}
}
