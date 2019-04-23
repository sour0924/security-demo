/**
 * 
 */
package com.hello.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Administrator
 * 核心配置
 */
@ConfigurationProperties(prefix="security")
public class SecurityProperties {
	//login页面配置
	private BrowserProperties browserProperties = new BrowserProperties();

	public BrowserProperties getBrowserProperties() {
		return browserProperties;
	}

	public void setBrowserProperties(BrowserProperties browserProperties) {
		this.browserProperties = browserProperties;
	}
	
}
