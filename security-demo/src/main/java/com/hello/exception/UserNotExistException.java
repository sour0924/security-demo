package com.hello.exception;

/**
 * 自定义异常
 * @author Administrator
 *
 */
public class UserNotExistException extends RuntimeException {

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 7742705776989994149L;
	
	private String id;
	
	public UserNotExistException(String id) {
		super("user not exists");
		this.id=id;
	}
}
