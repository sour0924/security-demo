package com.hello.web.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.hello.web.filter.TimeFilter;
import com.hello.web.interceptor.TimeInterceptor;
/**
 * 过滤器配置
 * @author Administrator
 *
 */
@Configuration//声明是一个配置类
public class WebConfig extends WebMvcConfigurerAdapter{
	
	@Autowired
	private TimeInterceptor timeInterceptor;//
	
	//任务处理
	@Override
	public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
		//configurer.setTaskExecutor(taskExecutor);
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(timeInterceptor);
	}
	
//	@Bean
	public FilterRegistrationBean timeFilter() {
		//注册,需要将自定义过滤器放入其中
		FilterRegistrationBean bean = new FilterRegistrationBean();
		
		TimeFilter timeFilter = new TimeFilter();
		bean.setFilter(timeFilter);
		
		//设置过滤那些请求
		List<String> urls = new ArrayList<String>();
		urls.add("/*");//表示过滤所有请求
		bean.setUrlPatterns(urls);
		
		//返回
		return bean;
	}
}
