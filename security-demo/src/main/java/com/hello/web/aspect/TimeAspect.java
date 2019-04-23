/**
 * 
 */
package com.hello.web.aspect;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.hibernate.validator.internal.util.privilegedactions.NewInstance;
import org.mockito.internal.stubbing.answers.ThrowsException;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 * 切片 可以获取类名 方法名 参数
 */
//@Aspect
//@Component
public class TimeAspect {
	@Around("execution(* com.hello.web.controller.UserController2.*(..))")
	public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable{
		System.out.println("time aspect start");
		//获取参数
		Object[] args = pjp.getArgs();
		for (Object arg : args) {
			System.out.println("arg is "+arg);
		}
		
		Long start = new Date().getTime();
		Object object = pjp.proceed();
		System.out.println("time aspect 耗时:"+(new Date().getTime()-start));
		System.out.println("time aspect end");
		return object;
	}
}
