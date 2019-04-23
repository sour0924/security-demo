/**
 * 
 */
package com.hello.validator;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * @author Administrator
 * 自定义注解.进行校验
 */

//声明可以在哪里进行注解
//@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Target({ElementType.METHOD,ElementType.FIELD})//方法,字段上
//@Retention(RUNTIME)
@Retention(RetentionPolicy.RUNTIME)//运行时
@Constraint(validatedBy = {MyConstraintValidator.class })//使用自己的校验逻辑
public @interface MyConstraint {
	//以下3项,必备
	String message();
	Class<?>[] groups() default { };
	Class<? extends Payload>[] payload() default { };
}
