/**
 * 
 */
package com.hello.security.browser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.hello.security.core.properties.SecurityProperties;
import com.hello.security.core.validate.code.ValidateCodeFilter;

/**
 * @author Administrator
 *    配置类
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private SecurityProperties securityProperties;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
		//validateCodeFilter.setAuthenticationFailureHandler();
		
		//http.httpBasic()
		http
			.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
			.formLogin()
			.loginPage("/authentication/require")
			.loginProcessingUrl("/authentication/form")
			//成功或失败信息
			.and()
			.authorizeRequests()
			//允许登录页
			.antMatchers("/authentication/require",
					securityProperties.getBrowser().getLoginPage(),
					"/code/image"
					).permitAll()
			.anyRequest()
			.authenticated()
			.and()
			.csrf().disable();
	}
}
