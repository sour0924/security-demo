package com.hello.security.core.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 * 自定义异常
 * @author Administrator
 *
 */
public class ValidateCodeException extends AuthenticationException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 67209238015615648L;

	public ValidateCodeException(String msg) {
		super(msg);
	}

}
