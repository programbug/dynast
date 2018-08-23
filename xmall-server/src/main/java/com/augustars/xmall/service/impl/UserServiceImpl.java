package com.augustars.xmall.service.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.augustars.xmall.entity.User;
import com.augustars.xmall.service.UserService;

//SpringBoot采用全注解，实现类需获取配置文件，可以进行组建Bean组件
//让Spring框架可以找到该实现类
@Service("userService")
//需要让Spring找到Dubbo的服务
@com.alibaba.dubbo.config.annotation.Service(version="1.0.0")
//处理事务
@Transactional
public class UserServiceImpl implements UserService{
	public User getUserByLogibName(String loginName) throws Exception {
		User user = new User();
		user.setLoginName("admin");
		user.setPassword("21232f297a57a5a743894a0e4a801fc3");
		user.setStatus("Y");
		user.setUsername("李四");
		return user;
	}
	
}
