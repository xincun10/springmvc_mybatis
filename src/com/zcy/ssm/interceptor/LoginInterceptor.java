package com.zcy.ssm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 登录认证拦截器
 * @author Administrator
 *
 */
public class LoginInterceptor implements HandlerInterceptor {

	//进入handler方法之前执行
	//用于身份认证、身份授权
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		//获取请求的url
		String url = request.getRequestURI();
		//判断url是否为公开的地址（实际使用时将公开抵制配置在配置文件当中）
		//这里公开地址是登录时提交的地址
		if(url.indexOf("login.action")>=0)
		{
			//如果进行登录提交，放行
			return true;
		}
		//判断session
		HttpSession session = request.getSession();
		//从session中取出用户身份信息
		String name = (String) session.getAttribute("name");
		if(name!=null)
		{
			//身份存在，放行
			return true;
		}
		//用户需要认证，跳转到登录页面
		request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
		return false;
	}	

	//进入Handler方法之后，返回modelAndView之前执行
	//应用场景从modelAndView出发：将共用的模型数据在这里传到视图，也可以在这里统一指定视图
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
	}

	//执行Handler完成执行此方法
	//应用场景：统一异常处理，统一日志处理
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
