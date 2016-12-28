package com.server.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		System.out.println("进入拦截器");
		//对于不需要登录就可以访问(公开模块)，应该放行
		String uri = request.getRequestURI();		//得到访问的url地址
		if(uri.indexOf("login.action") != -1 || uri.indexOf("login.jsp") != -1 ){
			return true;		//放行
		}
		
		//判断session中是否有username
		if( null != request.getSession().getAttribute("loginInfo")){
			return true;		//session中有用户登录的信息，就放行
		}
		//表示即不是共有模块，也没有登录，这种情况下就不放行，并返回到登陆页面
		response.sendRedirect("login.jsp");
		//如果返回值为true表示放心，如果为false表示不放行
		return false;
	}

}
