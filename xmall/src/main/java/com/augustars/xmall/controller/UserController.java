package com.augustars.xmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.augustars.xmall.base.controller.BaseController;
import com.augustars.xmall.entity.User;

@Controller("userController")
@RequestMapping("/user")
public class UserController extends BaseController{
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ModelAndView loginError() throws Exception {
		System.out.println("认证失败！");
		//认证失败，调用该方法，重定向到失败界面		
		String url = request.getContextPath() + "/user/login";
		RedirectView view = new RedirectView(url);
		return new ModelAndView(view);
	}
	
	@RequestMapping(value="/test")
	@ResponseBody
	public String testSession() throws Exception {
		//获取Session
		User user = (User) session.getAttribute("user");
		System.out.println(user.getUsername());
		
		return "Hello Session";
	}
}
