package com.augustars.xmall.xtest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.augustars.xmall.base.controller.BaseController;
import com.augustars.xmall.xtest.service.HelloService;

@Controller("testController")
public class TestController extends BaseController{
	//Dubbo所提供的注解，从远程中心的注册表中引用接口的实现类
	//版本号必须与接口的实现类的版本号保持一致
	@Reference(version="1.0.0")
	private HelloService helloService;
	
	@RequestMapping(value="/test")
	@ResponseBody
	public String test(String name) throws Exception {
		helloService.sayHello(name);
		return name;
	}
}
