package com.hello.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.hello.dto.User;
import com.hello.dto.UserQueryCondition;
import com.hello.exception.UserNotExistException;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import com.hello.dto.User.UserDetailView;
import com.hello.dto.User.UserSimpleView;

/**
 * 代码重构版
 * 使用restful格式 get拿 put修 post存 delete删
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/user")
public class UserController2 {
	
	@GetMapping("me")
	public Object getCurrentUser(@AuthenticationPrincipal UserDetails user) {
		return user;
	}
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
	@GetMapping
	@JsonView(UserSimpleView.class)
	@ApiOperation(value="用户查询服务")
	public List<User> query(UserQueryCondition userQueryCondition,
			@PageableDefault(page=2,size=17,sort="name,desc")Pageable pageable){
		
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
	@GetMapping("/{id:\\d+}")
	@JsonView(UserDetailView.class)
	public User getInfo(@ApiParam("用户id") @PathVariable String id) {//@PathVariable获取参数并给参数赋值
//		throw new UserNotExistException(id);
		System.out.println("进入getInfo服务......");
		User user = new User();
		user.setName("tom");
		return user;
	}
	
	/**
	 * 校验与@Valid一块使用
	 * 校验报错后,是无法进入程序,可以使用bindresult先绑定错误进入,之后遍历拿到错误处理
	 * @param user
	 * @return
	 * 异常进入create(@Valid @RequestBody User user,BindingResult errors)
	 */
	@PostMapping
	public User create(@Valid @RequestBody User user) {
		
		//处理错误,判断是否有错误
//		if(errors.hasErrors()) {
//			errors.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
//		}
		
		System.out.println(user.getName());
		System.out.println(user.getAge());
		System.out.println(user.getId());
		System.out.println(user.getBirthday());
		user.setId(1);
		return user;
	}
	
	/**
	 * 
	 * @param user
	 * @param errors
	 * @return
	 */
	@PutMapping("/{id:\\d+}")
	public User update(@Valid @RequestBody User user,BindingResult errors) {
		
		//处理错误,判断是否有错误
		if(errors.hasErrors()) {
			errors.getAllErrors().stream()//拿到所有错误
				.forEach(error -> {//遍历异常信息
					
							//自定义注解,可以不需要写以下2条
							FieldError filFieldError = (FieldError)error;
							//获取异常所在的字段及信息
							String message = filFieldError.getField()+" "+filFieldError.getDefaultMessage();
							
							
							System.out.println(error.getDefaultMessage());
							}
						);
				
		}
		
		System.out.println(user.getName());
		System.out.println(user.getAge());
		System.out.println(user.getId());
		System.out.println(user.getBirthday());
		user.setId(1);
		return user;
	}
	
	@DeleteMapping("/{id:\\d+}")
	public void delete(@PathVariable String id) {//String或int都可以
		System.out.println(id);
	}
}
