/**
 * 
 */
package com.hello.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.hello.service.HelloService;

/**
 * @author Administrator
 *
 */
public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object> {
	
	@Autowired
	private HelloService helloService;
	/**
	 * 初始化
	 */
	@Override
	public void initialize(MyConstraint constraintAnnotation) {
		System.out.println("init ...");
	}
	
	/**
	 * 
	 */
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		helloService.greeting("tom");
		System.out.println(value);
		return false;
	}

}
