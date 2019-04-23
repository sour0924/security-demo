package com.hello.web.controller;



import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.hello.dto.User;
import com.hello.dto.UserQueryCondition;
import com.hello.dto.User.UserDetailView;
import com.hello.dto.User.UserSimpleView;

@RestController
public class UserController {
	//get方式请求
	//@RequestParam中name与value是一样的,defaultValue默认值,required是否必须
//	@RequestMapping(value="/user",method=RequestMethod.GET)
//	public List<User> query(@RequestParam(name="name",required=false,defaultValue="tom") String namefd){
//		
//		System.out.println(namefd);
//		
//		List<User> users = new ArrayList<User>();
//		//新增3条数据
//		users.add(new User());
//		users.add(new User());
//		users.add(new User());
//		return users;
//	}
	
	//多条件筛选,封装实体
//	@RequestMapping(value="/user",method=RequestMethod.GET)
//	public List<User> query(UserQueryCondition userQueryCondition){
//		
//		System.out.println(ReflectionToStringBuilder.toString(userQueryCondition,ToStringStyle.MULTI_LINE_STYLE));
//		
//		List<User> users = new ArrayList<User>();
//		//新增3条数据
//		users.add(new User());
//		users.add(new User());
//		users.add(new User());
//		return users;
//	}
	
	/**
	 * 查询列表
	 * @param userQueryCondition
	 * @param pageable
	 * @return
	 */
	@RequestMapping(value="/user_old",method=RequestMethod.GET)
	@JsonView(UserSimpleView.class)
	public List<User> query(UserQueryCondition userQueryCondition,@PageableDefault(page=2,size=17,sort="name,desc")Pageable pageable){
		
		System.out.println(ReflectionToStringBuilder.toString(userQueryCondition,ToStringStyle.MULTI_LINE_STYLE));
		
		System.out.println(pageable.getPageSize());//页码
		System.out.println(pageable.getPageNumber());//条数
		System.out.println(pageable.getSort());//排序
		
		List<User> users = new ArrayList<User>();
		//新增3条数据
		users.add(new User());
		users.add(new User());
		users.add(new User());
		return users;
	}
	
	/**
	 * 单条详情查询
	 * @param id
	 * @return
	 */
	//@PathVariable获取参数并给参数赋值
	@RequestMapping(value="/user_old/{id:\\d+}",method=RequestMethod.GET)
	@JsonView(UserDetailView.class)
	public User getInfo(@PathVariable String id) {
		User user = new User();
		user.setName("tom");
		return user;
	}
}
