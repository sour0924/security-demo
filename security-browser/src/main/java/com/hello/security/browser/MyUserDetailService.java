/**
 * 
 */
package com.hello.security.browser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 *   自定义认证逻辑
 */
@Component
public class MyUserDetailService implements UserDetailsService {
	private Logger logger = LoggerFactory.getLogger(getClass());
	//这里可以从数据库拿到数据进行验证
	//@Autowired
	//private mapper

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("登录用户名"+username);
		//根据用户名查询用户信息
//		return new User(username,"123456",
//				AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
		//根据查询到的用户是否被冻结了
		String password = passwordEncoder.encode("123456");
		logger.info("数据库密码"+password);
		return new User(username,password,
				true,true,true,true,
				AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
	}

}
