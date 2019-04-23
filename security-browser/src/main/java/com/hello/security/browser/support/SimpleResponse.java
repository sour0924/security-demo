package com.hello.security.browser.support;
/**
 * 统一响应类
 * @author Administrator
 *
 */
public class SimpleResponse {
	private Object content;

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}

	public SimpleResponse(Object content) {
		super();
		this.content = content;
	}

	public SimpleResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
