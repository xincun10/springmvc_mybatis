package com.zcy.ssm.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 全局异常处理器
 * @author Administrator
 *
 */
public class CustomExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, 
			HttpServletResponse response, Object handler, Exception ex) {
		//handler就是处理器适配器要执行Handler对象（只有method）
		/*
		 * 解析出异常类型，
		 * 如果该异常类型是系统自定义的异常，直接取出异常信息，在错误页面展示；
		 * 如果该异常信息不是系统自定义的异常，构造一个自定义的异常类型，信息为“未知错误”
		 */
		CustomException customException = null;
		if(ex instanceof CustomException)
		{
			customException = (CustomException)ex;
		}
		else
		{
			customException = new CustomException("未知错误");
		}
		//错误信息
		String message = customException.getMessage();
		
		ModelAndView modelAndView = new ModelAndView();
		//将错误信息传到页面
		modelAndView.addObject("message", message);
		//指定错误页面
		modelAndView.setViewName("error");
		
		return modelAndView;
	}

}
