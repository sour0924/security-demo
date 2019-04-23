/**
 * 
 */
package com.hello.service.impl;

import org.springframework.stereotype.Service;

import com.hello.service.HelloService;

/**
 * @author Administrator
 *
 */
@Service
public class HelloServiceImpl implements HelloService {

	@Override
	public String greeting(String name) {
		System.out.println("greeting");
		return "hello"+name;
	}

}
