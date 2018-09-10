package com.zcy.ssm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	//登录方法
	@RequestMapping("/login")
	public String login(HttpSession session, String name, String pwd)
	{
		//在session中保存用户身份信息
		session.setAttribute("name", name);
		//重定向到商品列表页面
		return "redirect: items/queryItems.action";
	}
	
	//退出
	@RequestMapping("/logout")
	public String logout(HttpSession session) throws Exception
	{
		//清除session
		session.invalidate();
		//重定向到商品列表页面
		return "redirect: items/queryItems.action";
	}
}
