package com.hello.dto;

import java.sql.Date;

import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonView;
import com.hello.validator.MyConstraint;

public class User {
	//保证有的可以看到/看不到,比如返回id不想让用户看到
	public interface UserSimpleView{};//简易视图
	public interface UserDetailView extends UserSimpleView{};//详情视图
	
	private int id;
	
	@MyConstraint(message = "这是一个测试")
	private String password;
	//不能为空 可以自定义   ==>(message="姓名不能为空")
	@NotBlank
	private String name;
	
	private int age;
	//时间处理 (message="生日必须是过去的时间")
	@Past
	private Date birthday;
	
	@JsonView(UserSimpleView.class)
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	//用户名可以看到
	@JsonView(UserSimpleView.class)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	//详情中年龄看不到
	@JsonView(UserDetailView.class)
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
}
