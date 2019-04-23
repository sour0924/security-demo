package com.hello.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * 多个查询条件,可以封装为一个对象
 * @author Administrator
 *
 */
public class UserQueryCondition {
	private String name;
	@ApiModelProperty(value="用户年龄起始值")
	private int age;
	@ApiModelProperty(value="用户年龄终止值")
	private int ageTo;
	private String xxx;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getAgeTo() {
		return ageTo;
	}
	public void setAgeTo(int ageTo) {
		this.ageTo = ageTo;
	}
	public String getXxx() {
		return xxx;
	}
	public void setXxx(String xxx) {
		this.xxx = xxx;
	}
	
	
}
