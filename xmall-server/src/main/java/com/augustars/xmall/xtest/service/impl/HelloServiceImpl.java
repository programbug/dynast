package com.augustars.xmall.xtest.service.impl;

import org.springframework.stereotype.Service;

import com.augustars.xmall.xtest.service.HelloService;

//SpringBoot采用全注解，实现类需获取配置文件，可以进行组建Bean组件
//让Spring框架可以找到该实现类
@Service("helloService")
//需要让Spring找到Dubbo的服务
@com.alibaba.dubbo.config.annotation.Service(version="1.0.0")
public class HelloServiceImpl implements HelloService{

	public void sayHello(String name) throws Exception {
		System.out.println("您好！" + name);
		
	}

}
