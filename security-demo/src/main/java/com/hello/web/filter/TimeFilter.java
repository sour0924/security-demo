/**
 * 
 */
package com.hello.web.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.stereotype.Component;

/**
 * @author Administrator
 * 自定义过滤器
 * 测试运行时间
 * 以下是注解方式,还有配置方式WebConfig
 */
//@Component//加入spring容器中
public class TimeFilter implements Filter {

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 * 初始化
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("time filter init");
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("time filter start");
		long start = new Date().getTime();
		chain.doFilter(request, response);//放行
		System.out.println("time filter 耗时:"+(new Date().getTime()-start));//耗时
		System.out.println("time filter end");
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 * 销毁调用
	 */
	@Override
	public void destroy() {
		System.out.println("time filter destroy");
	}

}
