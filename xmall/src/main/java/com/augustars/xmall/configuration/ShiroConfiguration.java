package com.augustars.xmall.configuration;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.augustars.xmall.util.ShiroDBRealm;

@Configuration
public class ShiroConfiguration {
	@Bean
	public ShiroDBRealm ShiroDBRealm() {
		return new ShiroDBRealm();
	}
	/**
	 * 获得SecurityManagerBean
	 */
	@Bean
	public SecurityManager securityManager(ShiroDBRealm shiroDBRealm) {
		DefaultWebSecurityManager securityManager =
				new DefaultWebSecurityManager();
		securityManager.setRealm(ShiroDBRealm());
		return securityManager;
	}
	
	@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
		//获得Shiro过滤Bean组件shiroFilter
		ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
		
		//设定核心管理对象,规定请求的方式是POST请求
		shiroFilter.setSecurityManager(securityManager);
		
		//--在shiroFilter中设定，当需要进行认证时，所需要进行的重定向路径地址
		//配置当需要认证时，系统跳转到登录界面的请求地址
		shiroFilter.setLoginUrl("/user/login");
		//配置当认证成功后，默认的重定向的跳转地址
		//若在认证之前，就已经存在要跳转的地址，则shiro框架会自动向那个地址进行重定向
		shiroFilter.setSuccessUrl("/index");
		//设置默认的其他认证规则
		//对于存放在Webapp下的static文件中的静态资源来说，该文件的请求是不需要进行认证的
		Map<String, String> filterChainDefinitionMap = 
				new LinkedHashMap<String, String>();
		//静态资源无需认证anon
		filterChainDefinitionMap.put("/index", "anon");			//首页面
		filterChainDefinitionMap.put("/product/list", "anon");	//商品列表
		filterChainDefinitionMap.put("/product/detail", "anon");//商品订单
		filterChainDefinitionMap.put("/user/regist", "anon");				//注册请求
		//对于退出请求使用推出规则，无需认证
		filterChainDefinitionMap.put("/user/logout", "logout"); //退出
		//对于其他的请求需要认证
		filterChainDefinitionMap.put("/**", "authc");
		
		shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilter;
	}
}





