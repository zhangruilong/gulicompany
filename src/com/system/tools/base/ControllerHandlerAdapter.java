package com.system.tools.base;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ControllerHandlerAdapter implements HandlerAdapter{
	
	public boolean supports(Object handler) {
		return (handler instanceof Controller);
	}

	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		return ((Controller) handler).handleRequest(request, response);
	}

	public long getLastModified(HttpServletRequest request, Object handler) {
	
		return -1L;
	}

}
