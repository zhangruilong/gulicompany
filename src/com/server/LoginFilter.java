package com.server;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.server.pojo.LoginInfo;

public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        // 获得在下面代码中要用的request,response,session对象
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        HttpSession session = servletRequest.getSession();

        // 获得用户请求的URI
        String path = servletRequest.getRequestURI();
        String basepath = servletRequest.getContextPath();
        //System.out.println(path);
        
        // 从session里取员工工号信息
        Object comid = null;
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("loginInfo");
        if(null != loginInfo){
        	comid = loginInfo.getCompanyid();
        }

        /*创建类Constants.java，里面写的是无需过滤的页面
        for (int i = 0; i < Constants.NoFilter_Pages.length; i++) {

            if (path.indexOf(Constants.NoFilter_Pages[i]) > -1) {
                chain.doFilter(servletRequest, servletResponse);
                return;
            }
        }*/
        
        // 登陆页面无需过滤
        if(path.indexOf("/login.jsp") > -1 ) {
        	if(comid == null || "".equals(comid)) {
        		chain.doFilter(servletRequest, servletResponse);
        	}else{
                servletResponse.sendRedirect(basepath+"/main.jsp");
        	}
            return;
        }

        // 判断如果没有取到员工信息,就跳转到登陆页面
        if (comid == null || "".equals(comid)) {
            // 跳转到登陆页面
            servletResponse.sendRedirect(basepath+"/login.jsp");
        } else {
            // 已经登陆,继续此次请求
            chain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

}